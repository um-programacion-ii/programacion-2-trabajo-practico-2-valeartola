package biblioteca;

public abstract class RecursoDigital {
    private String titulo;
    private int id;

    public RecursoDigital(String titulo,int id) {
        this.titulo = titulo;
        this.id = id;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
