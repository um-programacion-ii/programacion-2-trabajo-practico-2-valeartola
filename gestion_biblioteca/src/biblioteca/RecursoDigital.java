package biblioteca;

public abstract class RecursoDigital implements RecursoDigitalInt{
    private String titulo;
    private int id;
    protected ServicioNotificaciones servicioNotificaciones;
    private Categoria categoria;


    public RecursoDigital(String titulo,int id, ServicioNotificaciones servicioNotificaciones, Categoria categoria) {
        this.titulo = titulo;
        this.id = id;
        this.servicioNotificaciones = servicioNotificaciones;
        this.categoria = categoria;

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

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | ID: " + id;
    }
    @Override
    public String getIdentificador(){
        return titulo;
    }
    public void mostrarInformacion() {
        System.out.println(this.toString());
    }

}
