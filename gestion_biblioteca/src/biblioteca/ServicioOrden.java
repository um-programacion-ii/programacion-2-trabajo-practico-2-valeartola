package biblioteca;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioOrden {
    public static List<RecursoDigital> ordenarPorTitulo(List<RecursoDigital> recursos) {
        return recursos.stream()
                .sorted(Comparator.comparing(RecursoDigital::getIdentificador))
                .toList();
    }
}
