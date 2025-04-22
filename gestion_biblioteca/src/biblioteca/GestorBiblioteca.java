package biblioteca;
import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {
    private List<RecursoDigital> recursos;

    public GestorBiblioteca() {
        recursos = new ArrayList<>();

    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }


    public RecursoDigital buscarRecursoPorTitulo(String titulo) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equalsIgnoreCase(titulo)) {
                return recurso;
            }
        }
        return null;
    }


    //Devuelve lista de recursos
    public List<RecursoDigital> getRecursos() {
        return recursos;
    }


}
