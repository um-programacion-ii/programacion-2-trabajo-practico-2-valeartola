package biblioteca;

import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Consola consola = new Consola();

        ServicioNotificacionesEmail mail = new ServicioNotificacionesEmail();
        ServicioNotificacionesSMS sms = new ServicioNotificacionesSMS();

        CategoriaRecurso tecnologia = CategoriaRecurso.TECNOLOGIA;
        CategoriaRecurso novela = CategoriaRecurso.NOVELA;
        CategoriaRecurso historia = CategoriaRecurso.HISTORIA;
        CategoriaRecurso romance = CategoriaRecurso.ROMANCE;
        CategoriaRecurso ciencia = CategoriaRecurso.CIENCIA;
        CategoriaRecurso interes = CategoriaRecurso.INTERES;
        CategoriaRecurso ficcion = CategoriaRecurso.FICCION;

        consola.mostrarMenu();

        Usuario usuario1 = new Usuario("Valentina", "Artola", "valeart@mail.com", 48965782, "26185964826");
        int opcion = 2;

        if (opcion == 2) {

            System.out.println("Se eligio la opcion: 2.Crear libro");

            Libro libro1 = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020, mail, CategoriaRecurso.INTERES);
            libro1.prestar(usuario1);


            System.out.println("Libro creado:");
            libro1.mostrarInformacion();
        } else {
            System.out.println("Opción no implementada todavía.");
        }


        RecursoDigital libro2 = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024, sms, CategoriaRecurso.ROMANCE);
        RecursoDigital revista = new Revista("Caras", 2, 30, sms, CategoriaRecurso.INTERES);
        RecursoDigital audiolibro1 = new Audiolibro("Dakota", 4, "Mercedes S.", sms, CategoriaRecurso.FICCION);

        System.out.println("=== Probar comportamiento consistente (LSP) ===");
        libro2.mostrarInformacion();
        revista.mostrarInformacion();
        audiolibro1.mostrarInformacion();

        consola.mostrarMenuRecurso(libro2);
        consola.mostrarMenuRecurso(audiolibro1);

        System.out.println("---Pruebas Gestor Biblioteca---");

        GestorBiblioteca gestorBiblioteca = new GestorBiblioteca();

        gestorBiblioteca.agregarUsuario(usuario1);

        Libro libro3  = new Libro("El Principito", 101, "Editorial Salamandra", "Antoine", 1943, mail, CategoriaRecurso.FICCION);
        gestorBiblioteca.agregarRecurso(libro2);
        gestorBiblioteca.agregarRecurso(libro3);
        gestorBiblioteca.agregarRecurso(revista);
        gestorBiblioteca.agregarRecurso(audiolibro1);



        RecursoDigital encontrado = gestorBiblioteca.buscarRecursoPorTitulo("El Principito");
        RecursoDigital encontradoRev = gestorBiblioteca.buscarRecursoPorTitulo("Nosotros en la luna");

        if(encontrado != null){
            encontrado.mostrarInformacion();
        }

        encontradoRev.mostrarInformacion();


        List<RecursoDigital> resultados = gestorBiblioteca.buscarPorTitulo("java");
        System.out.println("=== Resultados de búsqueda por título ===");
        for (RecursoDigital recurso : resultados) {
            recurso.mostrarInformacion();
        }

        consola.mostrarCategoriasDisponibles();

        List<RecursoDigital> filtroCategoria = gestorBiblioteca.filtrarPorCategoria(ficcion);

        System.out.println("=== Recursos en categoría: " + ficcion + " ===");
        for (RecursoDigital r : filtroCategoria) {
            r.mostrarInformacion();
        }

        consola.mostrarMenuOrdenamiento(gestorBiblioteca);

        System.out.println("---Pruebas Buscar Usuarios (Con try-catch)---");

        consola.buscarUsuarioPorId(gestorBiblioteca);

        ((Prestable) libro3).prestar(usuario1); //

        consola.prestarRecursos(libro3, usuario1);

        System.out.println("---Pruebas servicioPrestamo---");

        ServicioPrestamos servicioPrestamos = new ServicioPrestamos(gestorBiblioteca);
        servicioPrestamos.prestar(libro2, usuario1);

        consola.mostrarMenuPrestamos(gestorBiblioteca, servicioPrestamos, usuario1);

        System.out.println("---Pruebas servicioReserva---");

        ServicioReserva servicioReserva = new ServicioReserva();

        Reserva reserva1 = new Reserva(usuario1, audiolibro1);

        servicioReserva.agregarReserva(reserva1);
        servicioReserva.mostrarReservas();


        Notificaciones noti1 = new NotificacionesEmail("Tu libro fue prestado con éxito", usuario1.getMail());
        Notificaciones noti2 = new NotificacionesSMS("Recordatorio de devolución", usuario1.getTelefono());
        noti1.enviar();
        noti2.enviar();

        mail.cerrar();
        sms.cerrar();
    }


}
