package biblioteca.servicios;

import biblioteca.recursos.RecursoDigital;

import java.util.Comparator;
import java.util.List;

public class ServicioOrden {
    public static List<RecursoDigital> ordenarPorTitulo(List<RecursoDigital> recursos) {
        return recursos.stream()
                .sorted(Comparator.comparing(RecursoDigital::getIdentificador))
                .toList();
    }
}
