package model;

/**
 * Clase EntidadBase abstracta que define los atributos comunes para todas las entidades del dominio.
 * Proporciona la gestión del identificador único (ID) y el estado de eliminación lógica (Soft Delete).
 */
public abstract class EntidadBase {

    private Long id;
    private boolean eliminado;

    /**
     * Constructor completo utilizado para reconstruir objetos desde la base de datos.
     *
     * @param id Identificador único de la entidad.
     * @param eliminado Estado de eliminación lógica (true si está eliminado, false si está activo).
     */
    protected EntidadBase(Long id, boolean eliminado) {
        this.id = id;
        this.eliminado = eliminado;
    }

    /**
     * Constructor por defecto para la creación de nuevas instancias.
     * Inicializa el estado de 'eliminado' en false.
     */
    protected EntidadBase() {
        this.eliminado = false;
    }

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return El ID de la entidad, o null si aún no ha sido persistida.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la entidad.
     * Generalmente invocado por la capa de persistencia tras una inserción exitosa.
     *
     * @param id El nuevo ID asignado.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Verifica si la entidad ha sido marcada como eliminada lógicamente.
     *
     * @return true si la entidad está eliminada, false en caso contrario.
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * Establece el estado de eliminación lógica de la entidad.
     *
     * @param eliminado true para marcar como eliminado, false para activar.
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}