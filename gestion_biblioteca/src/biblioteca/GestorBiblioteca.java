package biblioteca;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GestorBiblioteca {
    private List<RecursoDigital> recursos;
    private Map<String, Usuario> usuarios;

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

    public RecursoDigital buscarRecursoPorTitulo(String titulo) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getIdentificador().equalsIgnoreCase(titulo)) {
                return recurso;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.get(id);
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
    public List<RecursoDigital> filtrarPorCategoria(Categoria categoria) {
        return recursos.stream()
                .filter(r -> r.getCategoria().equals(categoria))
                .toList();
    }
}
