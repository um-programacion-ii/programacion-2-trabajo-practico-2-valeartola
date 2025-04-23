package biblioteca.servicios;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.gestores.GestorBiblioteca;
import biblioteca.recursos.Prestamo;
import biblioteca.recursos.RecursoDigital;
import biblioteca.usuario.Usuario;

import java.util.Map;
import java.util.stream.Collectors;

public class ServicioReportes {

    private final GestorBiblioteca gestor;

    //Constructor
    public ServicioReportes(GestorBiblioteca gestor) {
        this.gestor = gestor;
    }

    public void reporteRecursosMasPrestados(int limite) {
        System.out.println("===Recursos mas prestados===");

        Map<RecursoDigital, Long> conteo = gestor.getPrestamos().stream()
                .collect(Collectors.groupingBy(Prestamo::getRecurso, Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<RecursoDigital, Long>comparingByValue().reversed())
                .limit(limite)
                .forEach(entry -> System.out.println(entry.getKey().getIdentificador() + " - Préstamos: " + entry.getValue()));
    }

    public void reporteUsuariosMasActivos(int limite) {
        System.out.println("===Usuarios mas activos===");

        Map<Usuario, Long> conteo = gestor.getPrestamos().stream()
                .collect(Collectors.groupingBy(Prestamo::getUsuario, Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<Usuario, Long>comparingByValue().reversed())
                .limit(limite)
                .forEach(entry -> System.out.println(entry.getKey().getNombre() + " - Préstamos: " + entry.getValue()));
    }

    public void reporteEstadisticasPorCategoria() {
        System.out.println("===Estadísticas por categoría===");

        Map<CategoriaRecurso, Long> conteo = gestor.getPrestamos().stream()
                .collect(Collectors.groupingBy(
                        p -> ((RecursoDigital) p.getRecurso()).getCategoria(),
                        Collectors.counting()
                ));

        conteo.forEach((cat, cantidad) ->
                System.out.println(cat + ": " + cantidad + " préstamos")
        );

    }
}