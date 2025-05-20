package org.example.agenda;

import dao.Contacto;
import dao.ContactoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Files;

public class ControllerUsuario {
    @FXML
    private TextField agregarNombreUsuario;
    @FXML
    private TextField agregarTelefonoContacto;
    @FXML
    private TextField agregarEmailContacto;
    @FXML
    private TextField agregarWebContacto;
    @FXML
    private Button agregarImagen;
    @FXML
    private Button agregarContacto;

    private File imagen;

    ContactoDAO clientes = new ContactoDAO();

    public void añadirImagen(){
        FileChooser selector = new FileChooser();
        selector.setTitle("Seleccionar imaxe");
        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imaxes", "*.png", "*.jpg", "*.jpeg")
        );

        // Supoñendo que xa tes acceso ao Stage principal
        imagen = selector.showOpenDialog(null);

    }

    public void agregarUsuario(ActionEvent event){
        String nombre = agregarNombreUsuario.getText();
        String telefono = agregarTelefonoContacto.getText();
        String email = agregarEmailContacto.getText();

        if (!nombre.isBlank() && !telefono.isBlank() && !email.isBlank() && imagen!=null) {

            agregarContacto.getScene().getWindow().hide();
            File resourcers;
            File carpetaUsuario = null;
            try {
                carpetaUsuario = new File(nombre + telefono);
                if (!carpetaUsuario.exists()) {
                    carpetaUsuario.mkdirs();
                }
                resourcers = new File(carpetaUsuario + "/" + imagen.getName());
                Files.copy(imagen.toPath(), resourcers.toPath());
            } catch (Exception e) {
                System.out.println("No funciono" + e);
            }

            Contacto e = new Contacto(nombre, telefono, email, carpetaUsuario + "/" + imagen.getName());
            clientes.guardar(e);
            ListaClientes.agregarCliente(e);


        }else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Faltan datos del contacto");
            a.setHeaderText("Faltan datos");
            a.show();
        }

    }

}
