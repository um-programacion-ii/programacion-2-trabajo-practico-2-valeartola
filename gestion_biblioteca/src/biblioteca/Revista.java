package biblioteca;

public class Revista extends RecursoDigital implements Prestable, Renovable{
    private int numeroEdicion;

    public Revista(String titulo, int id, int numeroEdicion) {
        super(titulo, id);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }
    @Override
    public String toString() {
        return super.toString() + " | Nº edición: " + numeroEdicion;
    }
    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }
    @Override
    public boolean estaDisponible() {
        return true;
    }

    @Override
    public void prestar() {
        System.out.println("Revista prestado.");
    }

    @Override
    public void devolver() {
        System.out.println("Revista devuelto.");
    }

    @Override
    public void renovar() {
        System.out.println("Revista renovado.");
    }
}
