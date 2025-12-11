package config;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection()){
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e){
            throw new SQLException("Error al intentar la conexión con la base de datos" + e.getMessage(), e);
        }
    }
}
