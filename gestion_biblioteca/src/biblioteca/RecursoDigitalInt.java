package biblioteca;

public interface RecursoDigitalInt {
    String getIdentificador();
    void mostrarInformacion();
    CategoriaRecurso getCategoria();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}