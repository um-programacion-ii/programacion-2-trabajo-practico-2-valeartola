package biblioteca.servicios;
import biblioteca.alerta.AlertaVencimiento;
import biblioteca.estado.EstadoRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.interfaces.Prestable;
import biblioteca.recursos.Prestamo;
import biblioteca.recursos.RecursoDigital;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;


public class ServicioPrestamos {
    private GestorBiblioteca gestor;
    private final ServicioAlertas servicioAlertas;

    public ServicioPrestamos(GestorBiblioteca gestor) {
        this.gestor = gestor;
        this.servicioAlertas = new ServicioAlertas(gestor);
    }

    public void prestar(RecursoDigital recurso, Usuario usuario) {
        try {
            if (!(recurso instanceof Prestable)) {
                System.out.println("Este recurso no se puede prestar");
                return;
            }

            ((Prestable) recurso).prestar(usuario);

            recurso.actualizarEstado(EstadoRecurso.PRESTADO);

            Prestamo prestamo = new Prestamo(recurso, usuario);
            gestor.agregarPrestamo(prestamo);

            System.out.println("Préstamo exitoso: " + prestamo);

            List<AlertaVencimiento> alertas = servicioAlertas.obtenerAlertasPendientes();
            for (AlertaVencimiento alerta : alertas) {
                alerta.mostrarAlerta();
            }

        } catch (RecursoNoDisponibleException e) {
            System.out.println( e.getMessage());
        }
    }


    public void devolver(RecursoDigital recurso, Usuario usuario) {
        if (!(recurso instanceof Prestable)) {
            System.out.println("❌ Este recurso no se puede devolver.");
            return;
        }

        ((Prestable) recurso).devolver(usuario);
        recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);

        System.out.println("Devolucion exitosa");
    }

    public void agregarPrestamo(String titulo, String idUsuario) {
        List<RecursoDigital> encontrados = gestor.buscarPorTitulo(titulo);

        if (encontrados.isEmpty()) {
            throw new RecursoNoDisponibleException("Recurso no encontrado.");
        }

        RecursoDigital recurso = encontrados.get(0);

        Usuario usuario = gestor.buscarUsuarioPorId(idUsuario);

        prestar(recurso, usuario);
    }


    public void mostrarTodosLosPrestamos() {
        if (gestor.getPrestamos().isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            System.out.println("=== Préstamos Registrados ===");
            for (Prestamo p : gestor.getPrestamos()) {
                System.out.println(p);
                System.out.println("---------------");
            }
        }
    }


    public void mostrarPrestamosPorUsuario(Usuario usuario) {
        List<Prestamo> prestamos = gestor.getPrestamos();
        boolean encontrado = false;

        for (Prestamo p : prestamos) {
            if (p.getUsuario().getID() == usuario.getID()) {
                System.out.println(p);
                System.out.println("---------------");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Este usuario no tiene préstamos registrados.");
        }
    }
}