package service.impl;

import config.DatabaseConnection;
import dao.impl.HistoriaClinicaDaoImpl;
import dao.impl.PacienteDaoImpl;
import model.HistoriaClinica;
import model.Paciente;
import service.PacienteService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la lógica de negocio para la entidad {@link Paciente}.
 * <p>
 * Esta clase actúa como el <b>Gestor de Transacciones</b>. Es responsable de:
 * <ol>
 * <li>Abrir y cerrar la conexión a la base de datos (usando try-with-resources).</li>
 * <li>Controlar la atomicidad de las operaciones (commit/rollback).</li>
 * <li>Orquestar los DAOs necesarios para completar una operación de negocio.</li>
 * </ol>
 * </p>
 */
public class PacienteServiceImpl implements PacienteService {

    /**
     * Valida las reglas de negocio para un Paciente antes de persistirlo.
     * Checks: Nulidad, campos obligatorios y existencia de Historia Clínica inicial.
     *
     * @param p El paciente a validar.
     * @throws IllegalArgumentException Si alguna regla de validación falla.
     */
    private void validar(Paciente p) {
        if (p == null) throw new IllegalArgumentException("El paciente no puede ser nulo.");
        if (p.getDni() == null || p.getDni().isBlank()) throw new IllegalArgumentException("El DNI es obligatorio.");
        if (p.getNombre() == null || p.getNombre().isBlank()) throw new IllegalArgumentException("El Nombre es obligatorio.");
        if (p.getApellido() == null || p.getApellido().isBlank()) throw new IllegalArgumentException("El Apellido es obligatorio.");
        if (p.getHistoriaClinica() == null) throw new IllegalArgumentException("El paciente debe tener una Historia Clínica inicial.");
    }

    /**
     * Crea un nuevo Paciente y su Historia Clínica asociada en una única transacción atómica.
     * <p>
     * Flujo de la transacción:
     * <ol>
     * <li>Verifica que no exista un paciente con el mismo DNI.</li>
     * <li>Inserta el Paciente (y obtiene su ID autogenerado).</li>
     * <li>Asigna el ID del Paciente a la Historia Clínica.</li>
     * <li>Inserta la Historia Clínica.</li>
     * <li>Si todo es exitoso, realiza commit. Si falla, realiza rollback.</li>
     * </ol>
     * </p>
     *
     * @param p El objeto Paciente (que contiene la Historia Clínica anidada).
     * @return El Paciente persistido con su ID actualizado.
     * @throws SQLException Si ocurre un error de base de datos no controlado.
     * @throws RuntimeException Si ocurre un error durante la transacción (provoca rollback).
     * @throws IllegalArgumentException Si el DNI ya existe.
     */
    @Override
    public Paciente create(Paciente p) throws SQLException {
        validar(p);

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try {
                PacienteDaoImpl pacienteDao = new PacienteDaoImpl(conn);

                if (pacienteDao.findByDni(p.getDni()).isPresent()) {
                    throw new IllegalArgumentException("Ya existe un paciente con el DNI: " + p.getDni());
                }

                // 1. Guardar Paciente
                pacienteDao.create(p);

                // 2. Vincular Historia con el nuevo ID del Paciente
                HistoriaClinica historia = p.getHistoriaClinica();
                historia.setPacienteId(p.getId());

                // 3. Guardar Historia Clínica
                HistoriaClinicaDaoImpl historiaDao = new HistoriaClinicaDaoImpl(conn);
                historiaDao.create(historia);

                conn.commit();

                return p;

            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Error en la transacción: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Busca un paciente por su DNI.
     * Operación de solo lectura.
     *
     * @param dni El documento a buscar.
     * @return Un Optional con el paciente si existe.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public Optional<Paciente> findByDni(String dni) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PacienteDaoImpl dao = new PacienteDaoImpl(conn);
            return dao.findByDni(dni);
        }
    }

    /**
     * Busca un paciente por su ID.
     * Operación de solo lectura.
     *
     * @param id El identificador del paciente.
     * @return Un Optional con el paciente si existe.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public Optional<Paciente> findById(Long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PacienteDaoImpl dao = new PacienteDaoImpl(conn);
            return dao.findById(id);
        }
    }

    /**
     * Recupera todos los pacientes del sistema.
     * Operación de solo lectura.
     *
     * @return Lista de pacientes.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public List<Paciente> getAll() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PacienteDaoImpl dao = new PacienteDaoImpl(conn);
            return dao.getAll();
        }
    }

    /**
     * Actualiza los datos personales de un paciente.
     * No afecta a la Historia Clínica (se debe usar el servicio de Historia para eso).
     *
     * @param p El paciente con datos modificados.
     * @throws SQLException Si ocurre un error de conexión.
     */
    @Override
    public void update(Paciente p) throws SQLException {
        validar(p);
        try (Connection conn = DatabaseConnection.getConnection()) {
            PacienteDaoImpl dao = new PacienteDaoImpl(conn);
            dao.update(p);
        }
    }

    /**
     * Realiza la baja lógica de un paciente y su historia clínica en cascada.
     * <p>
     * Se ejecuta en una transacción para asegurar que no queden datos inconsistentes
     * (por ejemplo, un paciente borrado pero con historia activa).
     * </p>
     *
     * @param id El ID del paciente a eliminar.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws RuntimeException Si falla la transacción.
     */
    @Override
    public void delete(Long id) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                HistoriaClinicaDaoImpl historiaDao = new HistoriaClinicaDaoImpl(conn);
                historiaDao.deleteByPacienteId(id);

                PacienteDaoImpl pacienteDao = new PacienteDaoImpl(conn);
                pacienteDao.delete(id);

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Error al eliminar paciente: " + e.getMessage());
            }
        }
    }
}