package biblioteca.servicios;

import biblioteca.recursos.NotificacionesSMS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void enviarNotificaciones(String mensaje, String destinatario) {
        NotificacionesSMS notificacion = new NotificacionesSMS(mensaje, destinatario); // o número dinámico si querés
        executor.execute(notificacion::enviar);
    }

    public void cerrar() {
        executor.shutdown();
    }
}
