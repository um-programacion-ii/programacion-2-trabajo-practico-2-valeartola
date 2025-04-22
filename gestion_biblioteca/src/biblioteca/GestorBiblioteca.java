package biblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GestorBiblioteca {
    private List<RecursoDigital> recursos;
    private Map<String, Usuario> usuarios;
    private List<Prestamo> prestamos = new ArrayList<>();

    public GestorBiblioteca() {
        recursos = new ArrayList<>();
        usuarios = new HashMap<>();

    }

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(String.valueOf(usuario.getID()), usuario);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public RecursoDigital buscarRecursoPorTitulo(String titulo) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equalsIgnoreCase(titulo)) {
                return recurso;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String id) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(String.valueOf(id));
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        return usuario;
    }

    public List<RecursoDigital> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getIdentificador().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }
    //Devuelve lista de recursos
    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public List<RecursoDigital> filtrarPorCategoria(CategoriaRecurso categoriaRecurso) {
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoriaRecurso)
                .toList();
    }

     public List<Prestamo> getPrestamos() {
        return prestamos;
    }
}
