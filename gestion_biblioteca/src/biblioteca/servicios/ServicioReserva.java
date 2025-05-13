package biblioteca.servicios;

import biblioteca.interfaces.RecursoDigitalInt;
import biblioteca.recursos.RecursoDigital;
import biblioteca.recursos.Reserva;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServicioReserva {
    private BlockingQueue<Reserva> colaReservas;

    public ServicioReserva() {
        this.colaReservas = new LinkedBlockingQueue<>();
    }

    public void agregarReserva(Reserva reserva){
        try {
            colaReservas.put(reserva);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error al agregar reserva ");
        }
    }

    public Reserva obtenerProximaReserva() {
        return colaReservas.poll();
    }

    public void mostrarReservas() {
        System.out.println("=== Reservas Pendientes ===");
        if (colaReservas.isEmpty()) {
            System.out.println(" No hay reservas pendientes ");
        } else {
            for (Reserva r : colaReservas) {
                System.out.println(r);
            }
        }
    }
    public Reserva obtenerSiguienteReserva(RecursoDigitalInt recurso) {
        for (Reserva reserva : colaReservas) {
            if (reserva.getRecurso().equals(recurso)) {
                return reserva;
            }
        }
        return null;
    }

    public void eliminarReserva(Reserva reserva) {
        colaReservas.remove(reserva);
    }
}