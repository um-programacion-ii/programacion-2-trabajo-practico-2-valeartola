package biblioteca.alerta;

import java.util.ArrayList;
import java.util.List;

public class HistorialAlertas {
    private List<String> historial = new ArrayList<>();

    public void registrarAlerta(String alerta) {
        historial.add(alerta);
    }

    public void mostrarHistorial() {
        System.out.println("=== HISTORIAL DE ALERTAS ===");
        for (String alerta : historial) {
            System.out.println(alerta);
        }
    }

    public List<String> getHistorial() {
        return historial;
    }
}
