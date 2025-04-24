package biblioteca.cli;


import biblioteca.consola.Consola;
import biblioteca.estado.CategoriaRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.interfaces.Prestable;
import biblioteca.recursos.*;
import biblioteca.servicios.*;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaApp {
    private Consola consola;
    private GestorBiblioteca gestorBiblioteca;
    private ServicioNotificacionesEmail mail;
    private ServicioNotificacionesSMS sms;
    private ServicioReserva servicioReserva;
    private ServicioPrestamos servicioPrestamos;
    private ServicioAlertas servicioAlertas;

    public BibliotecaApp() {
        consola = new Consola();
        gestorBiblioteca = new GestorBiblioteca();
        mail = new ServicioNotificacionesEmail();
        sms = new ServicioNotificacionesSMS();
        servicioReserva = new ServicioReserva();
        servicioPrestamos = new ServicioPrestamos(gestorBiblioteca, servicioReserva);
        servicioAlertas = new ServicioAlertas(gestorBiblioteca);
    }

    public void iniciar() {
        consola.mostrarMenu();

        Usuario usuario1 = new Usuario("Valentina", "Artola", "valeart@mail.com", 48965782, "26185964826");
        Usuario usuario2 = new Usuario("Mauro", "Codina", "mc@correo.com", 55888999, "123456789");
        Usuario usuario4 = new Usuario("Dakota", "Artola", "dakota@mail", 11222333, "2611122333");

        gestorBiblioteca.agregarUsuario(usuario1);
        gestorBiblioteca.agregarUsuario(usuario2);

        Libro libro1 = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020, mail, CategoriaRecurso.INTERES);
        Libro libro3 = new Libro("El Principito", 101, "Editorial Salamandra", "Antoine", 1943, mail, CategoriaRecurso.FICCION);
        RecursoDigital libro2 = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024, sms, CategoriaRecurso.ROMANCE);
        RecursoDigital revista = new Revista("Caras", 2, 30, sms, CategoriaRecurso.INTERES);
        RecursoDigital audiolibro1 = new Audiolibro("Dakota", 4, "Mercedes S.", sms, CategoriaRecurso.FICCION);

        gestorBiblioteca.agregarRecurso(libro1);
        gestorBiblioteca.agregarRecurso(libro2);
        gestorBiblioteca.agregarRecurso(libro3);
        gestorBiblioteca.agregarRecurso(revista);
        gestorBiblioteca.agregarRecurso(audiolibro1);

        consola.mostrarMenuRecurso(libro2);
        consola.mostrarMenuRecurso(audiolibro1);

        RecursoDigital encontrado = gestorBiblioteca.buscarRecursoPorTitulo("El Principito");
        if (encontrado != null) encontrado.mostrarInformacion();

        List<RecursoDigital> resultados = gestorBiblioteca.buscarPorTitulo("java");
        resultados.forEach(RecursoDigital::mostrarInformacion);

        consola.mostrarCategoriasDisponibles();

        List<RecursoDigital> filtroCategoria = gestorBiblioteca.filtrarPorCategoria(CategoriaRecurso.FICCION);
        filtroCategoria.forEach(RecursoDigital::mostrarInformacion);

        consola.mostrarMenuOrdenamiento(gestorBiblioteca);
        consola.buscarUsuarioPorId(gestorBiblioteca);

        consola.prestarRecursos(libro3, usuario1);
        servicioPrestamos.prestar(libro2, usuario1);
        consola.mostrarMenuPrestamos(gestorBiblioteca, servicioPrestamos, usuario1);

        Reserva reserva1 = new Reserva(usuario1, audiolibro1);
        Reserva reserva2 = new Reserva(usuario2, libro2);
        servicioReserva.agregarReserva(reserva1);
        servicioReserva.agregarReserva(reserva2);
        servicioReserva.mostrarReservas();

        consola.mostrarAlertas(gestorBiblioteca);
        servicioPrestamos.devolver(libro2, usuario1);
    }
}

