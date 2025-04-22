package biblioteca;

import java.time.LocalDate;

public class Prestamo {
    private RecursoDigital recurso;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(RecursoDigital recurso, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.recurso = recurso;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
