package dao;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class ConexionBD {
    private static final String CONFIG = "db.cfg";
    static final String clave = "prueba";

    public static Connection getConnection() throws ErrorDeSyntaxis {
        Connection conexion;

      /* try (BufferedReader lector = new BufferedReader(new FileReader(CONFIG))) {

            String linea;

            String user = (linea = lector.readLine()).startsWith("user") ? linea.substring((linea.indexOf(":") + 2)).trim() : "root";
            String pssw = (linea = lector.readLine()).startsWith("password") ? linea.substring((linea.indexOf(":") + 2)).trim() : "";
            String url = (linea = lector.readLine()).startsWith("url") ? linea.substring((linea.indexOf(":") + 2)).trim():"";

            conexion =  DriverManager.getConnection(url, user, pssw);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e);
            throw new ErrorDeSyntaxis("Falta informacion de acceso");

        }*/
        Properties propiedades = new Properties();
        try {
            var path = Paths.get("db.cnf");
            propiedades.load(Files.newInputStream(path));

            conexion =  DriverManager.getConnection((String) propiedades.get("url"), (String) propiedades.get("usuario"), (String) propiedades.get("password"));

        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (SQLException e){

            throw new ErrorDeSyntaxis("Falta informacion de acceso o esta mal escrita");
        }

        return conexion;
    }

    public static void iniciarSesion() throws IOException {
        Properties propiedades = new Properties();
        propiedades.setProperty("usuario","root");
        propiedades.setProperty("password","abc123.");
        propiedades.setProperty("url","jdbc:mysql://127.0.0.1:3306/agenda");
        var path = Paths.get("db.cnf");
        var oos = Files.newOutputStream(path);
        propiedades.store(oos,"");


    }

    public static SecretKeySpec obtenerResumenDeClave(String clave){

        SecretKeySpec cla = null;
        try {
            byte[] cadena = clave.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena,16);
            cla = new SecretKeySpec(cadena,"AES");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return cla;
    }

    public static String encript (String encriptar){
        String candenaEncriptada = "";
        try {
            SecretKeySpec resumen = obtenerResumenDeClave(clave);
            Cipher crip = Cipher.getInstance("AES");
            crip.init(Cipher.ENCRYPT_MODE, resumen);

            byte[] cadena = encriptar.getBytes(StandardCharsets.UTF_8);
            byte[] encriptado = crip.doFinal(cadena);
            candenaEncriptada = Base64.getEncoder().encodeToString(encriptado);
        }catch (Exception e){
            System.out.println(e);
        }

        return candenaEncriptada;
    }

    public static String desEncript (String desencryptar){
        String candenaDesEncriptada = "";
        try {
            SecretKeySpec resumen = obtenerResumenDeClave(clave);
            Cipher crip = Cipher.getInstance("AES");
            crip.init(Cipher.DECRYPT_MODE, resumen);

            byte[] cadena = Base64.getDecoder().decode(desencryptar);
            byte[] desencri = crip.doFinal(cadena);
            candenaDesEncriptada = new String(desencri);
        }catch (Exception e){
            System.out.println(e);
        }

        return candenaDesEncriptada;
    }

    public static void  closeConnection(Connection connection) {
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
