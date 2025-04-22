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
    public void prestar() {
        if (!estaDisponible()) {
            throw new RecursoNoDisponibleException("No se puede prestar el AUDIO LIBRO " + getTitulo() + " No disponible");
        }

        actualizarEstado(EstadoRecurso.PRESTADO);

        System.out.println("AudioLibro prestado.");
        servicioNotificaciones.enviarNotificaciones("Se presto el AudioLibro: " + getTitulo());
    }

    @Override
    public void devolver() {
        actualizarEstado(EstadoRecurso.DISPONIBLE);
        System.out.println("AudioLibro devuelto.");
        servicioNotificaciones.enviarNotificaciones("Se devolvio el AudioLibro: " + getTitulo());
    }
}
