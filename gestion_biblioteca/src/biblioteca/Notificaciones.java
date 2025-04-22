package biblioteca;

import java.time.LocalDateTime;

public abstract class Notificaciones {
    protected String mensaje;
    protected LocalDateTime fecha;

    //Constructor
    public Notificaciones(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    public abstract void enviar();

    @Override
    public String toString() {
        return "[" + fecha + "] " + mensaje;
    }
}