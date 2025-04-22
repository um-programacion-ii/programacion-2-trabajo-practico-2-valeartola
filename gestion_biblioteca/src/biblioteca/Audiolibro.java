package biblioteca;

public class Audiolibro extends RecursoDigital implements Prestable{
    private String canal;

    public Audiolibro(String titulo, int id, String canal, ServicioNotificaciones servicioNotificaciones, Categoria categoria) {
        super(titulo, id, servicioNotificaciones, categoria);
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
        return true;
    }

    @Override
    public void prestar() {
        System.out.println("Audiolibro prestado.");
        servicioNotificaciones.enviarNotificaciones("Se presto el AudioLibro: " + getTitulo());
    }

    @Override
    public void devolver() {
        System.out.println("Audiolibro devuelto.");
        servicioNotificaciones.enviarNotificaciones("Se devolvio el AudioLibro: " + getTitulo());
    }
}
