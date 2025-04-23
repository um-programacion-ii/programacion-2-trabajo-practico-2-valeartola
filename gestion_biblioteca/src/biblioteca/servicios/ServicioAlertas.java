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
                }
            }
        }
        return alertas;
    }
    public List<AlertaVencimiento> obtenerAlertasPorUsuario(Usuario usuario) {
        return gestor.getPrestamos().stream()
                .filter(p -> p.getUsuario().getID() == usuario.getID())
                .map(p -> new AlertaVencimiento(p, "Vencido"))
                .toList();
    }
}