package biblioteca.servicios;


import biblioteca.recursos.Notificaciones;
import biblioteca.recursos.NotificacionesEmail;
import biblioteca.recursos.NotificacionesSMS;
import biblioteca.recursos.TipoNotificacion;
import biblioteca.usuario.Usuario;

public class ServicioNotificadorPreferencia {
    public static void notificar(String mensaje, Usuario usuario) {
        if (usuario.getTipoNotificacion() == TipoNotificacion.EMAIL) {
            Notificaciones noti = new NotificacionesEmail(mensaje, usuario.getMail());
            noti.enviar();
        } else if (usuario.getTipoNotificacion() == TipoNotificacion.SMS) {
            Notificaciones noti = new NotificacionesSMS(mensaje, usuario.getTelefono());
            noti.enviar();
        }
    }
}