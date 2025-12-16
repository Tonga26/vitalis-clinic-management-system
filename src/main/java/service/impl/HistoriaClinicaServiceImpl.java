package service.impl;

import config.DatabaseConnection;
import dao.impl.HistoriaClinicaDaoImpl;
import model.HistoriaClinica;
import service.HistoriaClinicaService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la lógica de negocio para la entidad {@link HistoriaClinica}.
 * <p>
 * Gestiona las operaciones CRUD sobre las fichas médicas. Aunque muchas operaciones
 * son directas sobre una sola tabla, se mantiene el uso de transacciones (commit/rollback)
 * para garantizar la integridad y robustez ante fallos.
 * </p>
 */
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    /**
     * Valida que la historia clínica tenga los datos mínimos necesarios.
     *
     * @param h La historia clínica a validar.
     * @throws IllegalArgumentException Si es nula o falta el número de historia.
     */
    private void validar(HistoriaClinica h) {
        if (h == null) {
            throw new IllegalArgumentException("La Historia Clínica no puede ser nula.");
        }
        if (h.getNroHistoria() == null || h.getNroHistoria().isBlank()) {
            throw new IllegalArgumentException("El Nro. de Historia es obligatorio.");
        }
    }

    /**
     * Busca la Historia Clínica perteneciente a un paciente específico.
     *
     * @param pacienteId El ID del paciente dueño de la historia.
     * @return Un Optional con la historia si existe.
     * @throws SQLException Si ocurre error de conexión.
     */
    @Override
    public Optional<HistoriaClinica> findByPacienteId(Long pacienteId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()){
            HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
            return hcDao.findByPacienteId(pacienteId);
        }
    }

    /**
     * Realiza la baja lógica de la historia asociada a un paciente.
     * Ejecuta la operación dentro de una transacción.
     *
     * @param pacienteId El ID del paciente.
     * @throws SQLException Error de BD.
     * @throws RuntimeException Si falla la transacción.
     */
    @Override
    public void deleteByPacienteId(Long pacienteId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()){
            conn.setAutoCommit(false);
            try {
                HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
                hcDao.deleteByPacienteId(pacienteId);
                conn.commit();
            } catch (Exception e){
                conn.rollback();
                throw new RuntimeException("Error en la transacción: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Persiste una nueva Historia Clínica.
     * <p>
     * <b>Nota:</b> Generalmente la historia se crea junto con el paciente,
     * pero este método permite crearla independientemente si fuera necesario.
     * </p>
     *
     * @param hc La historia clínica a guardar.
     * @return La historia clínica con su ID generado.
     * @throws SQLException Error de BD.
     * @throws RuntimeException Si falla la transacción.
     */
    @Override
    public HistoriaClinica create(HistoriaClinica hc) throws SQLException {
        validar(hc);
        try (Connection conn = DatabaseConnection.getConnection()){
            conn.setAutoCommit(false);
            try {
                HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
                hcDao.create(hc);
                conn.commit();
            } catch (Exception e){
                conn.rollback();
                throw new RuntimeException("Error en la transacción: " + e.getMessage(), e);
            }
        }
        return hc;
    }

    /**
     * Busca una Historia Clínica por su identificador único (Primary Key).
     * Operación de solo lectura.
     *
     * @param id El ID único de la historia clínica.
     * @return Un Optional con la historia si existe y está activa.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public Optional<HistoriaClinica> findById(Long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()){
            HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
            return hcDao.findById(id);
        }
    }

    /**
     * Obtiene el listado completo de todas las Historias Clínicas activas en el sistema.
     * Útil para reportes o auditoría médica.
     *
     * @return Una lista de objetos HistoriaClinica.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public List<HistoriaClinica> getAll() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()){
            HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
            return hcDao.getAll();
        }
    }

    /**
     * Actualiza los datos de una historia clínica (Observaciones, medicación, etc.).
     * Operación transaccional.
     *
     * @param hc La historia con los datos nuevos.
     * @throws SQLException Error de BD.
     * @throws RuntimeException Si falla la transacción.
     */
    @Override
    public void update(HistoriaClinica hc) throws SQLException {
        validar(hc);
        try (Connection conn = DatabaseConnection.getConnection()){
            conn.setAutoCommit(false);
            try {
                HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
                hcDao.update(hc);
                conn.commit();
            } catch (Exception e){
                conn.rollback();
                throw new RuntimeException("Error en la transacción: " + e.getMessage(), e);
            }
        }

    }

    /**
     * Realiza la baja lógica de una Historia Clínica por su propio ID.
     * <p>
     * Se ejecuta dentro de una transacción para garantizar la consistencia
     * del cambio de estado (eliminado = true).
     * </p>
     *
     * @param id El ID de la historia a eliminar.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws RuntimeException Si falla la transacción (provoca rollback).
     */
    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()){
            conn.setAutoCommit(false);
            try {
                HistoriaClinicaDaoImpl hcDao = new HistoriaClinicaDaoImpl(conn);
                hcDao.delete(id);
                conn.commit();
            } catch (Exception e){
                conn.rollback();
                throw new RuntimeException("Error en la transacción: " + e.getMessage(), e);
            }
        }
    }
}