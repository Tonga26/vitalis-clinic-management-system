package model;

import java.time.LocalDate;

/**
 * Representa a un Paciente dentro del sistema de gestión.
 * <p>
 * Esta entidad actúa como el propietario de la relación uno a uno (1:1) unidireccional
 * con la clase {@link HistoriaClinica}. Extiende de {@link EntidadBase} para heredar
 * el identificador y el comportamiento de eliminación lógica.
 * </p>
 */
public class Paciente extends EntidadBase {

    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private HistoriaClinica historiaClinica;

    /**
     * Constructor por defecto.
     * Inicializa una instancia de Paciente lista para ser utilizada.
     * Necesario para frameworks y creación paso a paso.
     */
    public Paciente() {
        super();
    }

    /**
     * Constructor para crear un NUEVO paciente (Aún no persistido).
     * <p>
     * Se utiliza cuando el usuario ingresa los datos por primera vez.
     * El ID se inicializa en null y 'eliminado' en false.
     * </p>
     *
     * @param nombre          Nombre de pila.
     * @param apellido        Apellido.
     * @param dni             Documento único.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param historiaClinica La historia clínica asociada (puede ser null inicialmente).
     */
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, HistoriaClinica historiaClinica) {
        // Llama implícitamente a super() -> id=null, eliminado=false
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.historiaClinica = historiaClinica;
    }

    /**
     * Constructor completo para reconstruir un paciente EXISTENTE desde la Base de Datos.
     * <p>
     * Este constructor debe ser usado únicamente por la capa DAO (Data Access Object)
     * al mapear un ResultSet SQL a un objeto Java.
     * </p>
     *
     * @param id              El ID recuperado de la BD.
     * @param eliminado       El estado soft-delete recuperado de la BD.
     * @param nombre          Nombre de pila.
     * @param apellido        Apellido.
     * @param dni             Documento único.
     * @param fechaNacimiento Fecha de nacimiento.
     * @param historiaClinica La historia clínica asociada (o null si se carga perezosamente).
     */
    public Paciente(Long id, boolean eliminado, String nombre, String apellido, String dni, LocalDate fechaNacimiento, HistoriaClinica historiaClinica) {
        super(id, eliminado); // Pasa los datos de control al padre
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.historiaClinica = historiaClinica;
    }

    /**
     * Obtiene el nombre de pila del paciente.
     *
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de pila del paciente.
     *
     * @param nombre El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del paciente.
     *
     * @return El apellido del paciente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del paciente.
     *
     * @param apellido El nuevo apellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el Documento Nacional de Identidad (DNI).
     *
     * @return El DNI del paciente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el Documento Nacional de Identidad (DNI).
     *
     * @param dni El nuevo DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene la fecha de nacimiento del paciente.
     *
     * @return La fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del paciente.
     *
     * @param fechaNacimiento La nueva fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la Historia Clínica asociada a este paciente.
     *
     * @return La Historia Clínica, o null si no tiene una asignada.
     */
    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    /**
     * Asocia una Historia Clínica a este paciente.
     * Representa la relación unidireccional donde el Paciente conoce a su Historia Clínica.
     *
     * @param historiaClinica La Historia Clínica a asociar.
     */
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    /**
     * Genera una representación en cadena del objeto Paciente.
     * Incluye identificadores, datos personales y un resumen breve de la historia clínica.
     *
     * @return Cadena descriptiva del paciente.
     */
    @Override
    public String toString() {
        return "Paciente{id=" + getId() + ", dni='" + dni + "', nombre='" + nombre + "', apellido='" + apellido
                + "', hc=" + (historiaClinica != null ? historiaClinica.brief() : "null") + "}";
    }
}