package biblioteca.alerta;

import biblioteca.recursos.Prestamo;

import java.time.LocalDate;

public class AlertaVencimiento {
    private Prestamo prestamo;
    private String tipoAlerta;
    private NivelUrgencia nivelUrgencia;


    //Constructor
    public AlertaVencimiento(Prestamo prestamo, String tipoAlerta) {
        this.prestamo = prestamo;
        this.tipoAlerta = tipoAlerta;
        this.nivelUrgencia = calcularUrgencia(prestamo);

    }
    public NivelUrgencia calcularUrgencia(Prestamo prestamo) {

        LocalDate fechaActual = LocalDate.now();
        LocalDate devolucion = prestamo.getFechaDevolucion();

        if (devolucion == null) return NivelUrgencia.INFO;

        long dias = devolucion.toEpochDay() - fechaActual.toEpochDay();

        if (dias == 1 || dias == 0) return NivelUrgencia.WARNING;
        if (dias < 0) return NivelUrgencia.ERROR;
        return  NivelUrgencia.INFO;
    }

    public void mostrarAlerta() {

        if (!prestamo.getUsuario().deseaNotificar(nivelUrgencia)) return;

        LocalDate hoy = LocalDate.now();
        LocalDate fechaVencimiento = prestamo.getFechaDevolucion();
        long diasRestantes = hoy.until(fechaVencimiento).getDays();

        System.out.println("ALERTA [" + nivelUrgencia + "]: " + tipoAlerta.toUpperCase());


        if (diasRestantes == 0) {
            System.out.println("Hoy vence el préstamo del recurso: " + prestamo.getRecurso().getIdentificador());
        } else if (diasRestantes < 0) {
            System.out.println("Vencido → " + prestamo.getRecurso().getIdentificador());
        } else if (diasRestantes == 1) {
            System.out.println("Mañana vence el préstamo del recurso: " + prestamo.getRecurso().getIdentificador());
        } else {
            return;
        }
        System.out.println("Usuario: " + prestamo.getUsuario().getNombre());
        System.out.println("Fecha devolución esperada: " + fechaVencimiento);

    }

    public NivelUrgencia getNivelUrgencia() {
        return nivelUrgencia;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
}
