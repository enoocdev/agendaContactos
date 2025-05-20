package org.example.agenda;

import dao.Contacto;
import dao.ContactoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    private ContactoDAO cliente = new ContactoDAO();

    @FXML
    private Button agregarContacto;

    @FXML
    private TableView<Contacto> contacts;

    @FXML
    private TableColumn<Contacto, String> columnaNombre;

    @FXML
    private TableColumn<Contacto, String> columnaTel;

    @FXML
    private TableColumn<Contacto, String> columnaEmail;

    @FXML
    private ImageView userImg;

    @FXML
    private Label userName;


    @FXML
    private Label userTel;

    @FXML
    private Label userEmail;


    public void initialize(){


        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        ContextMenu contextMenu = new ContextMenu();

        MenuItem eliminar = new MenuItem("Eliminar");
        MenuItem editar = new MenuItem("editar");

        contextMenu.getItems().addAll(eliminar,editar);

        contacts.setContextMenu(contextMenu);

        contacts.setItems(ListaClientes.getContactos());

        eliminar.setOnAction((event) -> {
            Contacto seleccionado = contacts.getSelectionModel().getSelectedItem();

            cliente.borrar(seleccionado);
            ListaClientes.getContactos().remove(seleccionado);
            File imagenes = new File(seleccionado.getImagen());
            imagenes.delete();
            imagenes.getParentFile().delete();

        });

        contacts.setOnMouseClicked(mouseEvent -> {

                Contacto seleccionado = contacts.getSelectionModel().getSelectedItem();

                try{

                    File img = new File(seleccionado.getImagen());

                    userImg.setImage(new Image(img.toURI().toString()));
                }catch (Exception e){
                    System.out.println(e);
                }


                userName.setText("Nombre : " + seleccionado.getNombre());

                userEmail.setText("Email : " + seleccionado.getEmail());

                userTel.setText("Telefono : " + seleccionado.getTelefono());


        });
    }

    public void agregarContacto(ActionEvent actionEvent) {
        try {
            lanzarVentanaAgregarUsuario();
        }catch (Exception e){
            System.out.println(e);
        }



    }

    @FXML
    private void lanzarVentanaAgregarUsuario () throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agregarUsuario.fxml"));
        Parent root = fxmlLoader.load();

        Stage nuevaVentana = new Stage();
        nuevaVentana.setTitle("Agregar Usuario");
        nuevaVentana.setScene(new Scene(root));
        nuevaVentana.show();
    }
}