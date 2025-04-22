package biblioteca;

public abstract class RecursoDigital {
    private String titulo;
    private int id;
    protected ServicioNotificaciones servicioNotificaciones;


    public RecursoDigital(String titulo,int id, ServicioNotificaciones servicioNotificaciones) {
        this.titulo = titulo;
        this.id = id;
        this.servicioNotificaciones = servicioNotificaciones;

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

    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

}
