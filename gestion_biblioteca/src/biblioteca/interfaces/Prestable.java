package biblioteca.interfaces;

import biblioteca.usuario.Usuario;

public interface Prestable {
    boolean estaDisponible();
    void prestar(Usuario usuario);
    void devolver(Usuario usuario);
}
