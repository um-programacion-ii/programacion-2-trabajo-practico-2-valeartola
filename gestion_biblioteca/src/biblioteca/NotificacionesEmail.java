package biblioteca;

public class NotificacionesEmail extends Notificaciones{
    private String destinatarioMail;

    public NotificacionesEmail(String mensaje, String destinatario) {
        super(mensaje);
        this.destinatarioMail = destinatario;
    }

    @Override
    public void enviar() {
        System.out.println("Email a " + destinatarioMail + ": " + mensaje);
    }
}
