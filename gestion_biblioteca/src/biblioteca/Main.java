package biblioteca;

public class Main {
    public static void main(String[] args) {

        Consola consola = new Consola();
        ServicioNotificaciones mail = new ServicioNotificacionesEmail();
        ServicioNotificaciones sms = new ServicioNotificacionesSMS();
        consola.mostrarMenu();

        int opcion = 2;

        if (opcion == 2) {

            System.out.println("Se eligio la opcion: 2.Crear libro");

            Libro libro1 = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020, mail);
            libro1.prestar();


            System.out.println("Libro creado:");
            //Compruebo que sirva mostrarinformacion() de clase base
            libro1.mostrarInformacion();
        } else {
            System.out.println("Opción no implementada todavía.");
        }


        RecursoDigital libro2 = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024, sms);
        RecursoDigital revista = new Revista("Caras", 2, 30, sms);
        RecursoDigital audiolibro1 = new Audiolibro("Dakota", 4, "Mercedes S.", sms);

        System.out.println("=== Probar comportamiento consistente (LSP) ===");
        libro2.mostrarInformacion();
        revista.mostrarInformacion();
        audiolibro1.mostrarInformacion();

        consola.mostrarMenuRecurso(libro2);
        consola.mostrarMenuRecurso(audiolibro1);


    }
}
