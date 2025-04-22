package biblioteca;

public class Audiolibro extends RecursoDigital implements Prestable{
    private String canal;

    public Audiolibro(String titulo, int id, String canal, ServicioNotificaciones servicioNotificaciones, CategoriaRecurso categoriaRecurso) {
        super(titulo, id, servicioNotificaciones, categoriaRecurso);
        this.canal = canal;
    }

    public String getCanal() {
        return canal;
    }
    @Override
    public String toString() {
        return super.toString() + " | Canal: " + canal;
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
            throw new RecursoNoDisponibleException("No se puede prestar el AUDIO LIBRO " + getTitulo() + " No disponible");
        }

        actualizarEstado(EstadoRecurso.PRESTADO);

        System.out.println("AudioLibro prestado.");
        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se prestó el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }
    }

    @Override
    public void devolver(Usuario usuario) {
        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("AudioLibro devuelto.");
        if (servicioNotificaciones instanceof ServicioNotificacionesEmail) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el AudioLibro: " + getTitulo(), usuario.getMail());
        } else if (servicioNotificaciones instanceof ServicioNotificacionesSMS) {
            servicioNotificaciones.enviarNotificaciones("Se devolvió el AudioLibro: " + getTitulo(), usuario.getTelefono());
        }
    }
}
