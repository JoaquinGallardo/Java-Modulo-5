package util;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDb {

    static int port = 3310;

    static String nombreDb = "alke-wallet-2";
    private static final String URL =
            "jdbc:mysql://localhost:" + port + "/" + nombreDb;
    private static String usuario = "root";
    private static String pass = "caco4269";
    private static BasicDataSource pool;

    public static BasicDataSource getPool() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(usuario);
            pool.setPassword(pass);
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(10);
        }
        return pool;
    }

    public static Connection getConection() throws SQLException {
        return getPool().getConnection();
    }


//    public static void main(String[] args) throws SQLException {
//        Connection pool = getConnection();
//        if(pool == null) {
//            System.out.println("Conexion fallida");
//        } else{
//            System.out.println("Conexion exitosa");
//        }
//    }
}
