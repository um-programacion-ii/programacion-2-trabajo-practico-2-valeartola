package biblioteca;
import java.time.LocalDate;
import java.util.List;


public class ServicioPrestamos {
    private GestorBiblioteca gestor;

    public ServicioPrestamos(GestorBiblioteca gestor) {
        this.gestor = gestor;
    }

    public void prestar(RecursoDigital recurso, Usuario usuario) {
        try {
            if (!(recurso instanceof Prestable)) {
                System.out.println("Este recurso no se puede prestar");
                return;
            }

            ((Prestable) recurso).prestar(usuario); // esto ya lanza excepción si está prestado

            recurso.actualizarEstado(EstadoRecurso.PRESTADO);

            LocalDate fechaPrestamo = LocalDate.now();
            LocalDate fechaDevolucion = fechaPrestamo.plusDays(15);

            Prestamo prestamo = new Prestamo(recurso, usuario, fechaPrestamo, fechaDevolucion);
            gestor.agregarPrestamo(prestamo);

            System.out.println("Préstamo exitoso: " + prestamo);
        } catch (RecursoNoDisponibleException e) {
            System.out.println( e.getMessage());
        }
    }

    public void devolver(RecursoDigital recurso, Usuario usuario ) {
        if (!(recurso instanceof Prestable)) {
            System.out.println("❌ Este recurso no se puede devolver.");
            return;
        }

        ((Prestable) recurso).devolver(usuario);
        recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);

        System.out.println("Devolución exitosa");
    }

    public void agregarPrestamo(String titulo, String idUsuario) {
        List<RecursoDigital> encontrados = gestor.buscarPorTitulo(titulo);

        if (encontrados.isEmpty()) {
            throw new RecursoNoDisponibleException("Recurso no encontrado.");
        }

        RecursoDigital recurso = encontrados.get(0);

        Usuario usuario = gestor.buscarUsuarioPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

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