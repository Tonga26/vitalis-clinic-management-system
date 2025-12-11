package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Properties loadProps(){
        Properties p = new Properties();

        try (InputStream fis = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")){
            if (fis == null){
                throw new IOException("No se encontró el archivo db.properties en el Classpath. Verifique la carpeta src/main/resources");
            }

            p.load(fis);

        } catch (IOException e){
            throw new RuntimeException("Error al cargar el db.properties: " + e.getMessage(), e);
        }

        return p;
    }

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties p = loadProps();

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(p.getProperty("db.url"));
            config.setUsername(p.getProperty("db.user"));
            config.setPassword(p.getProperty("db.password"));
            config.setMaximumPoolSize(Integer.parseInt(p.getProperty("db.cant_max_con")));
            config.setMinimumIdle(Integer.parseInt(p.getProperty("db.cant_min_con")));

            dataSource = new HikariDataSource(config);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error al inicializar la conexión" + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
