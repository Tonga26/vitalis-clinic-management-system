package model;

import java.time.LocalDate;

/**
 * Representa a un Paciente dentro del sistema Vitalis.
 * <p>
 * Esta entidad extiende de {@link EntidadBase}, heredando la gestión de ID y borrado lógico.
 * Contiene los datos filiatorios del paciente y mantiene una asociación con su {@link HistoriaClinica}.
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
     * Inicializa una instancia vacía, útil para frameworks y asignación manual de propiedades.
     */
    public Paciente() {
        super();
    }

    /**
     * Constructor para crear un NUEVO paciente (aún no persistido en la BD).
     * El ID será null hasta que se guarde.
     *
     * @param nombre          Nombre(s) del paciente.
     * @param apellido        Apellido(s) del paciente.
     * @param dni             Documento Nacional de Identidad (único).
     * @param fechaNacimiento Fecha de nacimiento.
     */
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento) {
        super(); // Inicializa eliminado = false
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Constructor completo para reconstruir un paciente DESDE la base de datos.
     *
     * @param id              Identificador único recuperado de la BD.
     * @param eliminado       Estado de eliminación lógica recuperado de la BD.
     * @param nombre          Nombre(s) del paciente.
     * @param apellido        Apellido(s) del paciente.
     * @param dni             DNI del paciente.
     * @param fechaNacimiento Fecha de nacimiento.
     */
    public Paciente(Long id, boolean eliminado, String nombre, String apellido, String dni, LocalDate fechaNacimiento) {
        super(id, eliminado); // Pasa los datos de control al padre
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El apellido del paciente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido El apellido a establecer.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return El DNI del paciente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni El DNI a establecer.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return La fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento La fecha de nacimiento a establecer.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el objeto de Historia Clínica asociado.
     * Nota: Puede ser null si no se ha cargado explícitamente desde la BD (Lazy Loading).
     *
     * @return La historia clínica o null.
     */
    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    /**
     * Asocia una Historia Clínica a este paciente.
     *
     * @param historiaClinica El objeto HistoriaClinica a asociar.
     */
    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    /**
     * Representación en String del objeto.
     * Incluye una llamada a un método 'brief()' de HistoriaClinica para no generar ciclos infinitos.
     */
    @Override
    public String toString() {
        // OJO: Asegúrate de crear el método .brief() en tu clase HistoriaClinica
        // o cambiar esta línea si ese método no existe aún.
        String historiaInfo = (historiaClinica != null) ? historiaClinica.toString() : "null";

        return "Paciente{" +
                "id=" + getId() +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", hc=" + historiaInfo +
                '}';
    }
}