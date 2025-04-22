package biblioteca;

public interface Prestable {
    boolean estaDisponible();
    void prestar(Usuario usuario);
    void devolver(Usuario usuario);
}
