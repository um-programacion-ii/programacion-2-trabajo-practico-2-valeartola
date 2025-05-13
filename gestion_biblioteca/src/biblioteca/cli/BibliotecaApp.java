package biblioteca.cli;


import biblioteca.alerta.HistorialAlertas;
import biblioteca.alerta.NivelUrgencia;
import biblioteca.alerta.Recordatorios;
import biblioteca.consola.Consola;
import biblioteca.estado.CategoriaRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.interfaces.Prestable;
import biblioteca.interfaces.Renovable;
import biblioteca.recursos.*;
import biblioteca.servicios.*;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

public class BibliotecaApp {
    private Consola consola;
    private GestorBiblioteca gestorBiblioteca;
    private ServicioReserva servicioReserva;
    private ServicioPrestamos servicioPrestamos;
    private ServicioAlertas servicioAlertas;
    private HistorialAlertas historialAlertas;
    private Recordatorios recordatorios;
    private Usuario usuario1, usuario2, usuario4;
    private RecursoDigital libro1, libro2, libro3, revista, audiolibro1;


    public BibliotecaApp() {
        consola = new Consola();
        gestorBiblioteca = new GestorBiblioteca();
        servicioReserva = new ServicioReserva();
        servicioPrestamos = new ServicioPrestamos(gestorBiblioteca, servicioReserva);
        servicioAlertas = new ServicioAlertas(gestorBiblioteca);
        recordatorios = new Recordatorios(servicioAlertas, historialAlertas);
        historialAlertas = new HistorialAlertas();

    }
    public void iniciar() {
        crearUsuariosDemo();
        crearRecursosDemo();

        Usuario usuario = consola.seleccionarUsuario(gestorBiblioteca.getListaUsuarios());

        consola.menuPrincipal(
                usuario,
                gestorBiblioteca,
                servicioPrestamos,
                servicioAlertas,
                new ServicioReportes(gestorBiblioteca),
                servicioReserva
        );
    }

    private void crearUsuariosDemo() {
        usuario1 = new Usuario("Valentina", "Artola", 48965782, "valeart@mail.com", "26185964826", TipoNotificacion.EMAIL, NivelUrgencia.INFO);
        usuario2 = new Usuario("Mauro", "Codina", 55888999, "mc@correo.com", "123456789", TipoNotificacion.SMS, NivelUrgencia.INFO);
        usuario4 = new Usuario("Dakota", "Artola", 11222333, "dakota@mail", "2611122333", TipoNotificacion.EMAIL, NivelUrgencia.INFO);

        gestorBiblioteca.agregarUsuario(usuario1);
        gestorBiblioteca.agregarUsuario(usuario2);
        gestorBiblioteca.agregarUsuario(usuario4);
    }
    private void crearRecursosDemo() {
        libro1 = new Libro("Antes de que se enfrie el cafe", 1, "Penguin", "Toshikazu Kawaguchi", 2020, CategoriaRecurso.INTERES);
        libro3 = new Libro("El Principito", 101, "Editorial Salamandra", "Antoine", 1943, CategoriaRecurso.FICCION);
        libro2 = new Libro("Nosotros en la luna", 2, "Planeta", "Alice Kellen", 2024, CategoriaRecurso.ROMANCE);
        revista = new Revista("Caras", 2, 30, CategoriaRecurso.INTERES);
        audiolibro1 = new Audiolibro("Dakota", 4, "Mercedes S.", CategoriaRecurso.FICCION);

        gestorBiblioteca.agregarRecurso(libro1);
        gestorBiblioteca.agregarRecurso(libro2);
        gestorBiblioteca.agregarRecurso(libro3);
        gestorBiblioteca.agregarRecurso(revista);
        gestorBiblioteca.agregarRecurso(audiolibro1);
    }


}

