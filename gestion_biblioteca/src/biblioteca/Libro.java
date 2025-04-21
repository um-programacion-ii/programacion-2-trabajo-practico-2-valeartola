package biblioteca;

public class Libro extends RecursoDigital {
    private String editorial;
    private String autor;
    private int ano;

    public Libro(String titulo, int id, String editorial, String autor, int ano) {
        super(titulo, id);
        this.editorial = editorial;
        this.autor = autor;
        this.ano = ano;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }
}