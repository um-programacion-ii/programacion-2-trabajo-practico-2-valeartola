package biblioteca.recursos;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.estado.EstadoRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.interfaces.Prestable;
import biblioteca.servicios.ServicioNotificaciones;
import biblioteca.servicios.ServicioNotificacionesEmail;
import biblioteca.servicios.ServicioNotificacionesSMS;
import biblioteca.usuario.Usuario;

public class Audiolibro extends RecursoDigital implements Prestable {
    private String canal;

    public Audiolibro(String titulo, int id, String canal, ServicioNotificaciones servicioNotificaciones, CategoriaRecurso categoriaRecurso) {
        super(titulo, id, servicioNotificaciones, categoriaRecurso);
        this.canal = canal;
    }

    public String getCanal() {
        return canal;
    }
    @Override
    public String toString() {
        return super.toString() + " | Canal: " + canal;
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
            throw new RecursoNoDisponibleException("No se puede prestar el AUDIO LIBRO " + getTitulo() + " No disponible");
        }

        actualizarEstado(EstadoRecurso.PRESTADO);

        System.out.println("[HILO " + Thread.currentThread().getName() + "] Préstamo exitoso de: " + getTitulo());

        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public synchronized void devolver(Usuario usuario) {
        System.out.println("[HILO " + Thread.currentThread().getName() + "] → Intentando devolver: " + getTitulo());
        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("[HILO " + Thread.currentThread().getName() + "] Devolución exitosa de: " + getTitulo());
        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }
        notifyAll();
    }
}
