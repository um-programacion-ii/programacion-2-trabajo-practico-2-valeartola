package biblioteca;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Consola consola = new Consola();
        ServicioNotificaciones mail = new ServicioNotificacionesEmail();
        ServicioNotificaciones sms = new ServicioNotificacionesSMS();
        Categoria tecnologia = Categoria.TECNOLOGIA;
        Categoria novela = Categoria.NOVELA;
        Categoria historia = Categoria.HISTORIA;
        Categoria romance = Categoria.ROMANCE;
        Categoria ciencia = Categoria.CIENCIA;
        Categoria interes = Categoria.INTERES;
        Categoria ficcion = Categoria.FICCION;
        consola.mostrarMenu();

        int opcion = 2;

        if (opcion == 2) {

            System.out.println("Se eligio la opcion: 2.Crear libro");

            Libro libro1 = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020, mail, Categoria.INTERES);
            libro1.prestar();


            System.out.println("Libro creado:");
            //Compruebo que sirva mostrarinformacion() de clase base
            libro1.mostrarInformacion();
        } else {
            System.out.println("Opción no implementada todavía.");
        }


        RecursoDigital libro2 = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024, sms, Categoria.ROMANCE);
        RecursoDigital revista = new Revista("Caras", 2, 30, sms, Categoria.INTERES);
        RecursoDigital audiolibro1 = new Audiolibro("Dakota", 4, "Mercedes S.", sms, Categoria.FICCION);

        System.out.println("=== Probar comportamiento consistente (LSP) ===");
        libro2.mostrarInformacion();
        revista.mostrarInformacion();
        audiolibro1.mostrarInformacion();

        consola.mostrarMenuRecurso(libro2);
        consola.mostrarMenuRecurso(audiolibro1);
        System.out.println("---Pruebas Gestor Biblioteca---");

        GestorBiblioteca gestorBiblioteca = new GestorBiblioteca();

        Usuario usuario1 = new Usuario("Valentina", "Artola", "valeart@mail.com", 48965782);
        gestorBiblioteca.agregarUsuario(usuario1);

        Libro libro3  = new Libro("El Principito", 101, "Editorial Salamandra", "Antoine", 1943, mail, Categoria.FICCION);
        gestorBiblioteca.agregarRecurso(libro2);
        gestorBiblioteca.agregarRecurso(libro3);
        gestorBiblioteca.agregarRecurso(revista);
        gestorBiblioteca.agregarRecurso(audiolibro1);



        RecursoDigital encontrado = gestorBiblioteca.buscarRecursoPorTitulo("El Principito");
        RecursoDigital encontradoRev = gestorBiblioteca.buscarRecursoPorTitulo("Nosotros en la luna");

        //Le digo que muestre la informacion si lo encuentra (DEL LIBRO)
        if(encontrado != null){
            encontrado.mostrarInformacion();
        }

        //muestra la informacion (DE LA REVISTA)
        encontradoRev.mostrarInformacion();


        List<RecursoDigital> resultados = gestorBiblioteca.buscarPorTitulo("java");
        System.out.println("=== Resultados de búsqueda por título ===");
        for (RecursoDigital recurso : resultados) {
            recurso.mostrarInformacion();
        }


        List<RecursoDigital> filtroCategoria = gestorBiblioteca.filtrarPorCategoria(ficcion);

        System.out.println("=== Recursos en categoría: " + ficcion + " ===");
        for (RecursoDigital r : filtroCategoria) {
            r.mostrarInformacion();
        }

        consola.mostrarMenuOrdenamiento(gestorBiblioteca);

    }


}
