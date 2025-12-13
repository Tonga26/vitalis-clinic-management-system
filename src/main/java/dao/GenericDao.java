package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica que define las operaciones CRUD estándar (Create, Read, Update, Delete).
 * <p>
 * Utiliza genéricos para ser reutilizable por cualquier entidad del sistema.
 * Implementa Optional para evitar NullPointerExceptions en las búsquedas.
 * </p>
 *
 * @param <T> El tipo de la entidad (ej: Paciente, HistoriaClinica).
 */
public interface GenericDao<T> {

    /**
     * Persiste una nueva entidad en la base de datos.
     *
     * @param t La entidad a guardar.
     * @return La entidad guardada (posiblemente con su nuevo ID asignado).
     * @throws SQLException Si ocurre un error en la inserción.
     */
    T create(T t) throws SQLException;

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id El ID de la entidad a buscar.
     * @return Un {@link Optional} que contiene la entidad si existe, o vacío si no.
     * @throws SQLException Si ocurre un error de consulta.
     */
    Optional<T> getById(Long id) throws SQLException;

    /**
     * Recupera todos los registros de la tabla correspondiente.
     *
     * @return Una lista con todas las entidades encontradas (vacía si no hay registros).
     * @throws SQLException Si ocurre un error de consulta.
     */
    List<T> getAll() throws SQLException;

    /**
     * Actualiza los datos de una entidad existente.
     *
     * @param t La entidad con los datos modificados.
     * @throws SQLException Si ocurre un error en la actualización.
     */
    void update(T t) throws SQLException;

    /**
     * Elimina un registro de la base de datos por su ID.
     *
     * @param id El identificador del registro a eliminar.
     * @throws SQLException Si ocurre un error en el borrado.
     */
    void delete(Long id) throws SQLException;
}
