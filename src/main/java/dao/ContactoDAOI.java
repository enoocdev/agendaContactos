package dao;

import java.util.List;

public interface ContactoDAOI {
    Contacto buscar(int identificador) throws ErrorDeSyntaxis;
    Contacto buscar(Contacto c) throws ErrorDeSyntaxis;
    boolean guardar(Contacto c);
    boolean borrar(Contacto c);
    boolean modificar (Contacto c);
    List<Contacto> obtenerListado();

}