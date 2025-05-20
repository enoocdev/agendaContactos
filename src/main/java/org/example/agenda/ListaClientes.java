package org.example.agenda;

import dao.Contacto;
import dao.ContactoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListaClientes {
    private static final ContactoDAO cliente = new ContactoDAO();
    private static final ObservableList<Contacto> contactos = FXCollections.observableArrayList(cliente.obtenerListado());

    static void agregarCliente(Contacto e){
        contactos.add(e);
    }

    public static ObservableList<Contacto> getContactos() {
        return contactos;
    }


}
