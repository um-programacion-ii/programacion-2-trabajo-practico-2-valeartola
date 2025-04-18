public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected int ID;

    public Usuario(String nombre, String apellido, String mail, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.ID = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                "apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", id='" + ID + '\'' +
                '}';
    }}
