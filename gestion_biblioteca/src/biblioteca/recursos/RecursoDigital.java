package biblioteca.recursos;

import biblioteca.estado.CategoriaRecurso;
import biblioteca.estado.EstadoRecurso;
import biblioteca.interfaces.RecursoDigitalInt;

public abstract class RecursoDigital implements RecursoDigitalInt {
    private String titulo;
    private int id;
    private CategoriaRecurso categoriaRecurso;
    private EstadoRecurso estado = EstadoRecurso.DISPONIBLE;


    public RecursoDigital(String titulo, int id, CategoriaRecurso categoriaRecurso) {
        this.titulo = titulo;
        this.id = id;
        this.categoriaRecurso = categoriaRecurso;

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

    @Override
    public String toString() {
        return "Título: " + titulo + " | ID: " + id;
    }
    @Override
    public String getIdentificador(){
        return titulo;
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public void actualizarEstado(EstadoRecurso nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    public CategoriaRecurso getCategoria() {
        return categoriaRecurso;
    }

}
