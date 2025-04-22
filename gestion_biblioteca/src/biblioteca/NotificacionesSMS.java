package biblioteca;

public class NotificacionesSMS extends Notificaciones {
    private String destinatarioSMS;

    public NotificacionesSMS(String mensaje, String destinatario) {
        super(mensaje);
        this.destinatarioSMS = destinatario;
    }

    @Override
    public void enviar() {
        System.out.println("SMS a " + destinatarioSMS + ": " + mensaje);
    }
}
