package dao;

public class Contacto {
    private int codigo;
    private String nombre;
    private String telefono;
    private String email;
    private String imagen;


    public Contacto() {
    }

    public Contacto(int codigo, String nombre, String telefono, String email, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.imagen = imagen;
    }

    public Contacto(String nombre, String telefono, String email, String imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.imagen = imagen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
