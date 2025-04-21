package biblioteca;

public class Main {
    public static void main(String[] args) {
        Audiolibro podcast = new Audiolibro("Vida de millonarios", 1, "LaCruda");

        System.out.println("Título: " + podcast.getTitulo());
        System.out.println("Canal: " + podcast.getCanal());


        Consola consola = new Consola();

        consola.mostrarMenu();

        int opcion = 2;

        if (opcion == 2) {

            System.out.println("Se eligio la opcion: 2.Crear libro");

            Libro libro = new Libro("Antes de que se enfrie el café", 1, "Penguin", "Toshikazu Kawaguchi", 2020);

            System.out.println("Libro creado:");
            System.out.println(libro);
        } else {
            System.out.println("Opción no implementada todavía.");
        }
    }
}