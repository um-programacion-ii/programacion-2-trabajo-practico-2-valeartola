package biblioteca;

public class ServicioNotificacionesEmail implements ServicioNotificaciones{
    @Override
    public void enviarNotificaciones(String mensaje) {
        System.out.println("Enviar mail" + mensaje);
    }
}
