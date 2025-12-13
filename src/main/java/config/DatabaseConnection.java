package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase responsable de gestionar la conexión a la base de datos utilizando un Pool de Conexiones (HikariCP).
 * <p>
 * Implementa un patrón Singleton estático para garantizar que exista una única instancia del
 * DataSource (origen de datos) en toda la aplicación, optimizando el rendimiento y los recursos.
 * </p>
 */
public class DatabaseConnection {

    /**
     * Carga las propiedades de configuración desde el archivo 'db.properties'.
     *
     * @return Objeto {@link Properties} con las credenciales y configuraciones cargadas.
     * @throws RuntimeException Si ocurre un error de E/S o no se encuentra el archivo.
     */
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

    /**
     * Bloque estático de inicialización.
     * Se ejecuta una sola vez cuando la clase es cargada en memoria por la JVM.
     * Aquí se configura e inicializa el HikariDataSource.
     */
    static {
        try {
            Properties p = loadProps();

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(p.getProperty("db.url"));
            config.setUsername(p.getProperty("db.user"));
            config.setPassword(p.getProperty("db.password"));

            // Convertimos los Strings del properties a Enteros
            config.setMaximumPoolSize(Integer.parseInt(p.getProperty("db.cant_max_con")));
            config.setMinimumIdle(Integer.parseInt(p.getProperty("db.cant_min_con")));

            dataSource = new HikariDataSource(config);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error fatal al inicializar la conexión: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene una conexión activa del pool de conexiones.
     * <p>
     * Si no hay conexiones disponibles en el pool, este método esperará hasta que una se libere
     * o se alcance el tiempo de espera (timeout).
     * </p>
     *
     * @return Un objeto {@link Connection} listo para ejecutar sentencias SQL.
     * @throws SQLException Si ocurre un error al intentar obtener la conexión del pool.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Cierra el pool de conexiones y libera todos los recursos asociados.
     * <p>
     * Este método debe llamarse al finalizar la aplicación para asegurar un cierre limpio.
     * </p>
     */
    public static void closePool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}