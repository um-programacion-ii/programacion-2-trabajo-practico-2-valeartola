public abstract class RecursoDigital {
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public RecursoDigital(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public abstract void mostrarInformacion();
}
