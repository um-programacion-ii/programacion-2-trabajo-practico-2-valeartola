package biblioteca;

public class Audiolibro extends RecursoDigital implements Prestable{
    private String canal;

    public Audiolibro(String titulo, int id, String canal) {
        super(titulo, id);
        this.canal = canal;
    }

    public String getCanal() {
        return canal;
    }
    @Override
    public String toString() {
        return super.toString() + " | Canal: " + canal;
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
        System.out.println("Audiolibro prestado.");
    }

    @Override
    public void devolver() {
        System.out.println("Audiolibro devuelto.");
    }
}
