package biblioteca;

import java.util.ArrayList;

public class GestorRecursos {
    private ArrayList<RecursoDigital> recursos;

    public GestorRecursos() {
        recursos = new ArrayList<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public ArrayList<RecursoDigital> getRecursos() {
        return recursos;
    }
}
