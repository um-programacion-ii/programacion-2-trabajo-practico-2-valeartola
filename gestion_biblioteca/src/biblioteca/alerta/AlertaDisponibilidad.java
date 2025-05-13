package biblioteca.alerta;

import biblioteca.recursos.Reserva;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AlertaDisponibilidad {
    private final Reserva reserva;
    private final String mensaje;
    private NivelUrgencia nivelUrgencia;


    //Constructor
    public AlertaDisponibilidad(Reserva reserva) {
        this.reserva = reserva;
        this.mensaje = "El recurso reservado esta DISPONIBLE";
        this.nivelUrgencia = calcularUrgencia();

    }

    private NivelUrgencia calcularUrgencia() {
        return NivelUrgencia.INFO;
    }

    public void mostrarAlerta() {
        System.out.println("=== ALERTA DE DISPONIBILIDAD ===");
        System.out.println(mensaje);
        System.out.println("Recurso: " + reserva.getRecurso().getIdentificador());
        System.out.println("Reservado por: " + reserva.getUsuario().getNombre());
        System.out.println("¿Desea tomarlo prestado ahora? (si/no): ");
    }

    public Reserva getReserva() {
        return reserva;
    }
}
