package biblioteca.servicios;

import biblioteca.recursos.NotificacionesEmail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void enviarNotificaciones(String mensaje, String destinatario) {
        NotificacionesEmail notificacion = new NotificacionesEmail(mensaje, destinatario);
        executor.execute(notificacion::enviar);
    }

    public void cerrar() {
        executor.shutdown();
    }
}
