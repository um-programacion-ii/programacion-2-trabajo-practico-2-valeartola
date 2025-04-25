package biblioteca.servicios;

import biblioteca.alerta.AlertaVencimiento;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.recursos.Prestamo;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioAlertas {
    private GestorBiblioteca gestor;

    //Constructor
    public ServicioAlertas(GestorBiblioteca gestor) {
        this.gestor = gestor;
    }

    public List<AlertaVencimiento> obtenerAlertasPendientes(){
        LocalDate fechaActual = LocalDate.now();
        List<AlertaVencimiento> alertas = new ArrayList<>();

        for (Prestamo prestamo : gestor.getPrestamos()) {
            LocalDate vencimiento = prestamo.getFechaDevolucion();
            if (vencimiento != null) {
                if (vencimiento.equals(fechaActual)) {
                    alertas.add(new AlertaVencimiento(prestamo, "Hoy vence"));
                } else if (vencimiento.minusDays(1).equals(fechaActual)) {
                    alertas.add(new AlertaVencimiento(prestamo, "Mañana vence"));
                } else if (vencimiento.isBefore(fechaActual)) {
                    alertas.add(new AlertaVencimiento(prestamo, "Vencido"));
                }
            }
        }
        return alertas;
    }
    public List<AlertaVencimiento> obtenerAlertasPorUsuario(Usuario usuario) {
        LocalDate hoy = LocalDate.now();
        List<AlertaVencimiento> alertas = new ArrayList<>();

        for (Prestamo p : gestor.getPrestamos()) {
            if (p.getUsuario().getID() == usuario.getID()) {
                LocalDate vencimiento = p.getFechaDevolucion();
                if (vencimiento != null) {
                    if (vencimiento.equals(hoy)) {
                        alertas.add(new AlertaVencimiento(p, "Hoy vence"));
                    } else if (vencimiento.minusDays(1).equals(hoy)) {
                        alertas.add(new AlertaVencimiento(p, "Mañana vence"));
                    } else if (vencimiento.isBefore(hoy)) {
                        alertas.add(new AlertaVencimiento(p, "Vencido"));
                    }
                }
            }
        }

        return alertas;
    }
}