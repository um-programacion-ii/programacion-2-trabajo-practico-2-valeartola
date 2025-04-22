package biblioteca;

public class Libro extends RecursoDigital implements Renovable, Prestable {
    private String editorial;
    private String autor;
    private int ano;

    public Libro(String titulo, int id, String editorial, String autor, int ano, ServicioNotificaciones servicioNotificaciones, CategoriaRecurso categoriaRecurso) {
        super(titulo, id, servicioNotificaciones, categoriaRecurso);
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
    @Override
    public String toString() {
        return super.toString() + " | Editorial: " + editorial + " | Autor: " + autor + " | Ano: " + ano;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

    @Override
    public boolean estaDisponible() {
        return getEstado() == EstadoRecurso.DISPONIBLE;
    }

    @Override
    public void prestar(Usuario usuario) {
        if (!estaDisponible()) {
            throw new RecursoNoDisponibleException("No se puede prestar el LIBRO " + getTitulo() + " No disponible");
        }

        actualizarEstado(EstadoRecurso.PRESTADO);

        System.out.println("Libro prestado.");

        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el Libro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el Libro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public void devolver(Usuario usuario) {
        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("Libro devuelto.");
        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el Libro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el Libro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public void renovar(Usuario usuario ) {
        System.out.println("Libro renovado.");
        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se renovo el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se renovo el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }
    }

}