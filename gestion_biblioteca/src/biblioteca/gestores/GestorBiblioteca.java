package biblioteca.gestores;
import biblioteca.estado.CategoriaRecurso;
import biblioteca.excepciones.UsuarioNoEncontradoException;
import biblioteca.interfaces.RecursoDigitalInt;
import biblioteca.recursos.Prestamo;
import biblioteca.recursos.RecursoDigital;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GestorBiblioteca {
    private List<RecursoDigitalInt> recursos;
    private Map<String, Usuario> usuarios;
    private List<Prestamo> prestamos = new ArrayList<>();

    public GestorBiblioteca() {
        recursos = new ArrayList<>();
        usuarios = new HashMap<>();

    }

    public void agregarRecurso(RecursoDigitalInt recurso) {
        recursos.add(recurso);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(String.valueOf(usuario.getID()), usuario);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public RecursoDigitalInt buscarRecursoPorTitulo(String titulo) {
        for (RecursoDigitalInt recurso : recursos) {
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

    public List<RecursoDigitalInt> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(r -> r.getIdentificador().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }
    //Devuelve lista de recursos
    public List<RecursoDigitalInt> getRecursos() {
        return recursos;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public List<RecursoDigitalInt> filtrarPorCategoria(CategoriaRecurso categoriaRecurso) {
        return recursos.stream()
                .filter(r -> r.getCategoria() == categoriaRecurso)
                .toList();
    }

     public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public List<Usuario> getListaUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

}
