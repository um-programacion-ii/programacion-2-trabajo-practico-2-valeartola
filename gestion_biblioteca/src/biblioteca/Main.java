package biblioteca;

public class Main {
    public static void main(String[] args) {

        Consola consola = new Consola();

        consola.mostrarMenu();

        int opcion = 2;

        if (opcion == 2) {

            System.out.println("Se eligio la opcion: 2.Crear libro");

            Libro libro = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020);

            System.out.println("Libro creado:");
            //Compruebo que sirva mostrarinformacion() de clase base
            libro.mostrarInformacion();
        } else {
            System.out.println("Opción no implementada todavía.");
        }

        RecursoDigital libro = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024);
        RecursoDigital revista = new Revista("Caras", 2, 30);
        RecursoDigital audiolibro = new Audiolibro("Dakota", 4, "Mercedes S.");

        System.out.println("=== Probar comportamiento consistente (LSP) ===");
        libro.mostrarInformacion();
        revista.mostrarInformacion();
        audiolibro.mostrarInformacion();

    }
}
