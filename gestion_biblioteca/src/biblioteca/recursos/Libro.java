package biblioteca.recursos;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.estado.EstadoRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.interfaces.Prestable;
import biblioteca.interfaces.Renovable;
import biblioteca.servicios.ServicioNotificaciones;
import biblioteca.servicios.ServicioNotificacionesEmail;
import biblioteca.servicios.ServicioNotificacionesSMS;
import biblioteca.usuario.Usuario;

public class Libro extends RecursoDigital implements Renovable, Prestable {
    private String editorial;
    private String autor;
    private int ano;

    public Libro(String titulo, int id, String editorial, String autor, int ano, ServicioNotificaciones servicioNotificaciones, CategoriaRecurso categoriaRecurso) {
        super(titulo, id, servicioNotificaciones, categoriaRecurso);
        this.editorial = editorial;
        this.autor = autor;
        this.ano = ano;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }
    @Override
    public String toString() {
        return super.toString() + " | Editorial: " + editorial + " | Autor: " + autor + " | Ano: " + ano;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    @Override
    public synchronized boolean estaDisponible() {
        return getEstado() == EstadoRecurso.DISPONIBLE;
    }

    @Override
    public synchronized void prestar(Usuario usuario) {
        System.out.println("[HILO " + Thread.currentThread().getName() + "] → Intentando prestar: " + getTitulo());

        if (!estaDisponible()) {
            System.out.println("[HILO " + Thread.currentThread().getName() + "] No disponible para préstamo.");
            throw new RecursoNoDisponibleException("No se puede prestar el LIBRO " + getTitulo() + " No disponible");
        }

        actualizarEstado(EstadoRecurso.PRESTADO);
        System.out.println("[HILO " + Thread.currentThread().getName() + "] Préstamo exitoso de: " + getTitulo());

        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el Libro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el Libro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public synchronized void devolver(Usuario usuario) {
        System.out.println("[HILO " + Thread.currentThread().getName() + "] → Intentando devolver: " + getTitulo());

        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("[HILO " + Thread.currentThread().getName() + "] Devolución exitosa de: " + getTitulo());

        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el Libro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el Libro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public synchronized void renovar(Usuario usuario) {
        System.out.println("[HILO " + Thread.currentThread().getName() + "] → Intentando renovar: " + getTitulo());

        if (!getEstado().equals(EstadoRecurso.PRESTADO)) {
            System.out.println("[HILO " + Thread.currentThread().getName() + "] No se puede renovar: el recurso no está prestado.");
            return;
        }

        System.out.println("[HILO " + Thread.currentThread().getName() + "] Renovación exitosa de: " + getTitulo());

        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se renovo el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se renovo el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }

    }


}