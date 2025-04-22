package biblioteca;

import java.time.LocalDateTime;

public class Reserva {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDateTime fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    @Override
    public String toString() {
        return "Reserva de " + recurso.getIdentificador() + " por " + fechaReserva;
    }
}
