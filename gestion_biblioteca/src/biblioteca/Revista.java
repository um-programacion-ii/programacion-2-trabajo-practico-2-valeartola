package biblioteca;

public class Revista extends RecursoDigital{
    private int numeroEdicion;

    public Revista(String titulo, int id, int numeroEdicion) {
        super(titulo, id);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }
}
