package biblioteca;

public class Categoria {
    private String titulo;

    public Categoria(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Categoria)) return false;
        Categoria otra = (Categoria) obj;
        return this.titulo.equals(otra.titulo);
    }

    @Override
    public int hashCode() {
        return titulo.hashCode();
    }
}
