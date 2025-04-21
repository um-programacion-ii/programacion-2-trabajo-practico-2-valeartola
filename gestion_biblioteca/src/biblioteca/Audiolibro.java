package biblioteca;

public class Audiolibro extends RecursoDigital{
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
}
