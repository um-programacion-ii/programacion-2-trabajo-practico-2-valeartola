package biblioteca.recursos;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.estado.EstadoRecurso;
import biblioteca.excepciones.RecursoNoDisponibleException;
import biblioteca.interfaces.Prestable;
import biblioteca.servicios.ServicioNotificadorPreferencia;
import biblioteca.usuario.Usuario;

public class Audiolibro extends RecursoDigital implements Prestable {
    private String canal;

    public Audiolibro(String titulo, int id, String canal, CategoriaRecurso categoriaRecurso) {
        super(titulo, id, categoriaRecurso);
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

        ServicioNotificadorPreferencia.notificar("Se prestó el AudioLibro: " + getTitulo(), usuario);

    }

    @Override
    public synchronized void devolver(Usuario usuario) {
        System.out.println("[HILO " + Thread.currentThread().getName() + "] → Intentando devolver: " + getTitulo());
        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("[HILO " + Thread.currentThread().getName() + "] Devolución exitosa de: " + getTitulo());        ServicioNotificadorPreferencia.notificar("Se devolvió el AudioLibro: " + getTitulo(), usuario);
        ServicioNotificadorPreferencia.notificar("Se devolvió el AudioLibro: " + getTitulo(), usuario);
        notifyAll();
    }
}
