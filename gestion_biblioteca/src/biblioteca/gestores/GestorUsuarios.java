package biblioteca.gestores;

import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public class GestorUsuarios {
    private ArrayList<Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarPorID(String id) {
        for (Usuario u : usuarios) {
            if (id.equals(u.getID())) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
