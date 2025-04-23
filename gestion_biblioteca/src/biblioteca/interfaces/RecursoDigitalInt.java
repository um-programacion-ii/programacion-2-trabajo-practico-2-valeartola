package biblioteca.interfaces;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.estado.EstadoRecurso;

public interface RecursoDigitalInt {
    String getIdentificador();
    void mostrarInformacion();
    CategoriaRecurso getCategoria();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}