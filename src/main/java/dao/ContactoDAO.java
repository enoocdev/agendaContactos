package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO implements ContactoDAOI {

    @Override
    public Contacto buscar(int identificador) throws ErrorDeSyntaxis {
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "SELECT * FROM contactos WHERE id = " + identificador;
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            return new Contacto(result.getInt("id"),result.getString("nombre"),result.getString("telefono"),result.getString("email"),result.getString("imagen"));
        }catch (Exception e){
            throw new ErrorDeSyntaxis("Error al buscar el usuario usuario no valido");
        }
    }

    @Override
    public Contacto buscar(Contacto c) throws ErrorDeSyntaxis {
        try {

            Connection con = ConexionBD.getConnection();

            String sql = "SELECT * FROM contactos WHERE id = " + c.getCodigo();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            result.next();
            return new Contacto(result.getInt("id"),result.getString("nombre"),result.getString("telefono"),result.getString("email"),result.getString("imagen"));
        }catch (Exception e){
            throw new ErrorDeSyntaxis("Error al buscar el usuario usuario no valido");
        }
    }

    @Override
    public boolean guardar(Contacto c) {
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "INSERT INTO contactos (nombre,telefono,email,imagen) VALUES ( \"" + c.getNombre() + "\",\"" + c.getTelefono() + "\",\"" + c.getEmail() + "\",\"" + c.getImagen()+ "\");";
            System.out.println(sql);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean borrar(Contacto c) {
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "DELETE FROM contactos WHERE id = " + c.getCodigo();
            Statement statement = con.createStatement();
            statement.execute(sql);
            return true;
        }catch (Exception e){
            System.out.println("No se ha encontrado al usuario o el id es invalido");
        }
        return false;
    }

    @Override
    public boolean modificar(Contacto c) {
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "UPDATE contactos SET nombre = \"" + c.getNombre() + "\" apellido = \"" + c.getTelefono() +"\" dni = \"" + c.getEmail() +  "\" direccion = \"" + c.getImagen() + "\" WHERE id = " + c.getCodigo();
            Statement statement = con.createStatement();
            statement.execute(sql);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Contacto> obtenerListado() {
        List<Contacto> clientes = new ArrayList<>();
        try {
            Connection con = ConexionBD.getConnection();
            String sql = "SELECT * FROM contactos";
            Statement statement = con.createStatement();
            ResultSet listaClientes = statement.executeQuery(sql);

            while (listaClientes.next()){
                clientes.add(new Contacto(listaClientes.getInt("id"),listaClientes.getString("nombre"),listaClientes.getString("telefono"),listaClientes.getString("email"),listaClientes.getString("imagen")));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return clientes;
    }
}
