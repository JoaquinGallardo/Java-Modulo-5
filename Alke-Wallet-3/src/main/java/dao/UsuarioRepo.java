package dao;



import model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo extends Dao implements Repositorio<Usuario> {

    public UsuarioRepo() {
        this.connectionDb();
    }

    private Usuario crearUsuario(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("id"));
        usuario.setNombre(resultSet.getString("nombre"));
        usuario.setApellido(resultSet.getString("apellido"));
        usuario.setRut(resultSet.getString("rut"));
        usuario.setCorreo_electronico(resultSet.getString("correo_electronico"));
        usuario.setContrasena(resultSet.getString("contrasena"));
        usuario.setSaldo(resultSet.getInt("saldo"));
        return usuario;

    }

    public static void main(String[] args) {
        UsuarioRepo repo = new UsuarioRepo();
        Usuario usuario = new Usuario("Oskar", "Pinochet", "12345678-9");
        try {
            List<Usuario> usuarios = repo.leer();
            System.out.println(usuarios);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int crear(Usuario usuario) throws SQLException {
        String sql = String.format("INSERT INTO usuario (id, nombre, apellido, rut)" + "VALUES ('%d', '%s', '%s', '%s')"
                , usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRut());

        return this.ejecutarSql(sql);
    }

    @Override
    public List<Usuario> leer() throws SQLException {
        String str = "SELECT * FROM usuario";
        this.consultar(str);
        List<Usuario> usuarios = new ArrayList<>();
        while(rs.next()) {
            Usuario usuario = crearUsuario(rs);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    @Override
    public int actualizar(Usuario usuario) throws SQLException {
        return 0;
    }

    @Override
    public int eliminar(int id) throws SQLException {
        return 0;
    }
}