package biblioteca;

public class Usuario {
    protected String nombre;
    protected String apellido;
    protected String mail;
    protected int ID;
    private String telefono;

    public Usuario(String nombre, String apellido, String mail, int dni, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.ID = dni;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Biblioteca.Usuario{" +
                "nombre='" + nombre + '\'' +
                "apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", id='" + ID + '\'' +
                '}';
    }}
