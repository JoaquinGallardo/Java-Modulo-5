package repositorio;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {
    //implemetar CRUD

    //Create
    void crear(T t) throws SQLException;

    //Read
    List<T> leer() throws SQLException;

    //Update
    void actualizar(T t) throws SQLException;

    //Delete
    void eliminar(int id) throws SQLException;

}