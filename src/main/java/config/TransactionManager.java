package config;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gestor de transacciones que envuelve una conexión JDBC para manejar operaciones atómicas.
 * Implementa la interfaz AutoCloseable para asegurar que los recursos se liberen
 * correctamente, realizando un rollback automático si la transacción no se completó.
 */
public class TransactionManager implements AutoCloseable {

    private Connection conn;
    private boolean transactionActive;

    /**
     * Constructor que inicializa el gestor con una conexión existente.
     *
     * @param conn La conexión a la base de datos.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     * @throws IllegalArgumentException Si la conexión proporcionada es nula.
     */
    public TransactionManager(Connection conn) throws SQLException {
        if (conn == null) {
            throw new IllegalArgumentException("La conexión no puede ser null");
        }
        this.conn = conn;
        this.transactionActive = false;
    }

    /**
     * Obtiene la conexión JDBC subyacente.
     *
     * @return La conexión gestionada por este administrador.
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Inicia una nueva transacción deshabilitando el auto-commit.
     *
     * @throws SQLException Si la conexión no está disponible o está cerrada.
     */
    public void startTransaction() throws SQLException {
        if (conn == null) {
            throw new SQLException("No se puede iniciar la transacción: conexión no disponible");
        }
        if (conn.isClosed()) {
            throw new SQLException("No se puede iniciar la transacción: conexión cerrada");
        }
        conn.setAutoCommit(false);
        transactionActive = true;
    }

    /**
     * Confirma los cambios realizados durante la transacción actual.
     *
     * @throws SQLException Si no hay conexión, no hay una transacción activa o falla el commit.
     */
    public void commit() throws SQLException {
        if (conn == null) {
            throw new SQLException("Error al hacer commit: no hay conexión establecida");
        }
        if (!transactionActive) {
            throw new SQLException("No hay una transacción activa para hacer commit");
        }
        conn.commit();
        transactionActive = false;
    }

    /**
     * Revierte los cambios realizados durante la transacción actual.
     * Captura internamente cualquier excepción SQL durante el proceso.
     */
    public void rollback() {
        if (conn != null && transactionActive) {
            try {
                conn.rollback();
                transactionActive = false;
            } catch (SQLException e) {
                System.err.println("Error durante el rollback: " + e.getMessage());
            }
        }
    }

    /**
     * Cierra la conexión y libera los recursos.
     * Si hay una transacción activa al momento de cerrar, se ejecuta un rollback automático.
     * Restaura el estado de auto-commit a true antes de cerrar.
     */
    @Override
    public void close() {
        if (conn != null) {
            try {
                if (transactionActive) {
                    rollback();
                }
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Verifica si hay una transacción actualmente en curso.
     *
     * @return true si hay una transacción activa, false en caso contrario.
     */
    public boolean isTransactionActive() {
        return transactionActive;
    }
}