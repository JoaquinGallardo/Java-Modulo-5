package repositorio;


import util.ConexionDb;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioRepo implements Repositorio<Usuario> {

    private Connection obtenerConexion() throws SQLException {
        return ConexionDb.getConection();
    }

    private Usuario crearUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNombre(resultSet.getString("nombre"));
        usuario.setRut(resultSet.getString("rut"));
        return usuario;

    }

    public static void main(String[] args) {
        UsuarioRepo repo = new UsuarioRepo();
        Usuario usuario = new Usuario( "Oskar", "Pinochet", "123456789-3");
        try {
            repo.crear(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void crear(Usuario usuario) throws SQLException {
        String sql = String.format("INSERT INTO usuario (id, nombre, apellido, rut)" + "VALUES ('%d', '%s', '%s', '%s')"
                , usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRut());

        try (Connection connection = obtenerConexion();

             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> leer() throws SQLException {
        return null;
    }

    @Override
    public void actualizar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(int id) throws SQLException {

    }
}