package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {

    // Configuración de la base de datos Neon
    private static final String URL = "jdbc:postgresql://ep-restless-breeze-amck179r.c-5.us-east-1.aws.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASS = "npg_1UHoVjTdb2rc";

    /**
     * Devuelve una conexión activa a la base de datos
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeConnection(Connection cn)throws SQLException {
        cn.close();
    }

}
