package biblioteca.servicios;
import biblioteca.alerta.AlertaDisponibilidad;
import biblioteca.alerta.AlertaVencimiento;
import biblioteca.estado.EstadoRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.interfaces.Prestable;
import biblioteca.interfaces.RecursoDigitalInt;
import biblioteca.recursos.Prestamo;
import biblioteca.recursos.RecursoDigital;
import biblioteca.recursos.Reserva;
import biblioteca.usuario.Usuario;
import biblioteca.consola.Consola;

import java.util.List;
import java.util.Scanner;


public class ServicioPrestamos {
    private GestorBiblioteca gestor;
    private final ServicioAlertas servicioAlertas;
    private final ServicioReserva servicioReserva;


    public ServicioPrestamos(GestorBiblioteca gestor, ServicioReserva servicioReserva) {
        this.gestor = gestor;
        this.servicioAlertas = new ServicioAlertas(gestor);
        this.servicioReserva = new ServicioReserva();
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

        System.out.print("¿Desea realizar una reserva de este recurso? (si/no): ");
        String respuesta = Consola.scanner.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            Reserva reserva = new Reserva(usuario, recurso);
            servicioReserva.agregarReserva(reserva);
            System.out.println("Reserva realizada para " + usuario.getNombre());
        } else {
            System.out.println("No se realizó la reserva.");
        }
    }


    public void devolver(RecursoDigitalInt recurso, Usuario usuario) {
        if (!(recurso instanceof Prestable)) {
            System.out.println("Este recurso no se puede devolver.");
            return;
        }

        ((Prestable) recurso).devolver(usuario);
        recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);

        System.out.println("Devolución exitosa");
        Reserva siguienteReserva = servicioReserva.obtenerSiguienteReserva(recurso);

        if (siguienteReserva != null) {
            AlertaDisponibilidad alerta = new AlertaDisponibilidad(siguienteReserva);
            alerta.mostrarAlerta();

            Consola.scanner.nextLine();
            String respuesta = Consola.scanner.nextLine();

            if (respuesta.equalsIgnoreCase("si")) {
                try {
                    prestar((RecursoDigital) recurso, siguienteReserva.getUsuario());
                    servicioReserva.eliminarReserva(siguienteReserva);
                    System.out.println("El recurso fue prestado al usuario que lo había reservado.");
                } catch (RecursoNoDisponibleException e) {
                    System.out.println("El recurso ya no está disponible.");
                }
            } else {
                System.out.println("El usuario decidió no tomar el recurso por ahora.");
            }
        }
    }
    private void ofrecerRecursosDisponibles(Usuario usuario) {

        List<RecursoDigitalInt> disponibles = gestor.getRecursos().stream()
                .filter(r -> r instanceof Prestable && ((Prestable) r).estaDisponible())
                .toList();

        if (disponibles.isEmpty()) {
            System.out.println("No hay recursos disponibles en este momento.");
        } else {
            System.out.println("Recursos disponibles para préstamo:");
            for (RecursoDigitalInt r : disponibles) {
                System.out.println(r.getIdentificador());
            }

            System.out.print("¿Desea tomar alguno en préstamo? (si/no): ");
            String tomarOtro = Consola.scanner.nextLine();

            if (tomarOtro.equalsIgnoreCase("si")) {
                System.out.print("Ingrese el TITULO del recurso que desea tomar: ");
                String idTitulo = Consola.scanner.nextLine();


                List<RecursoDigitalInt> coincidencias = gestor.getRecursos().stream()
                        .filter(r -> r instanceof Prestable && ((Prestable) r).estaDisponible())
                        .toList();

                if (!coincidencias.isEmpty()) {
                    RecursoDigitalInt recursoElegido = coincidencias.get(0);
                    prestar((RecursoDigital) recursoElegido, usuario);
                } else {
                    System.out.println("❌ Recurso no encontrado o no disponible.");
                }
            }
        }
    }

    public void agregarPrestamo(String titulo, String idUsuario) {
        List<RecursoDigitalInt> encontrados = gestor.buscarPorTitulo(titulo);

        if (encontrados.isEmpty()) {
            throw new RecursoNoDisponibleException("Recurso no encontrado.");
        }

        RecursoDigitalInt recurso = encontrados.get(0);

        Usuario usuario = gestor.buscarUsuarioPorId(idUsuario);

        prestar((RecursoDigital) recurso, usuario);
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