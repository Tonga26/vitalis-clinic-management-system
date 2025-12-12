package model;

import java.time.LocalDate;

/**
 * Entidad que representa la Historia Clínica de un paciente.
 * <p>
 * Contiene la información médica relevante como el grupo sanguíneo, antecedentes,
 * medicación actual y observaciones. Esta clase es la parte dependiente
 * de la relación uno a uno con {@link Paciente}.
 * </p>
 */
public class HistoriaClinica extends EntidadBase {

    /**
     * Enumeración que define los tipos de grupo sanguíneo y factor Rh válidos.
     * Gestiona la conversión entre la representación en base de datos (String)
     * y la constante de enumeración en Java.
     */
    public enum GrupoSanguineo {
        A_POS("A+"), A_NEG("A-"), B_POS("B+"), B_NEG("B-"),
        AB_POS("AB+"), AB_NEG("AB-"), O_POS("O+"), O_NEG("O-");

        private final String db;

        GrupoSanguineo(String db) {
            this.db = db;
        }

        /**
         * Obtiene la representación en cadena del grupo sanguíneo para almacenamiento en BD.
         *
         * @return El String correspondiente al grupo (ej. "A+").
         */
        public String db() {
            return db;
        }

        /**
         * Convierte un String proveniente de la base de datos en su correspondiente Enum.
         *
         * @param s El string del grupo sanguíneo (ej. "O-").
         * @return La constante GrupoSanguineo correspondiente, o null si el input es null.
         * @throws IllegalArgumentException Si el string no coincide con ningún grupo válido.
         */
        public static GrupoSanguineo fromDb(String s) {
            if (s == null) {
                return null;
            }
            for (GrupoSanguineo g : values()) {
                if (g.db.equalsIgnoreCase(s)) {
                    return g;
                }
            }
            throw new IllegalArgumentException("Grupo sanguíneo inválido: " + s);
        }
    }

    private String nroHistoria;
    private GrupoSanguineo grupoSanguineo;
    private String antecedentes;
    private String medicacionActual;
    private String observaciones;
    private LocalDate fechaApertura;
    private Long pacienteId;

    /**
     * Constructor por defecto.
     */
    public HistoriaClinica() {
        super();
    }

    /**
     * Constructor para crear una NUEVA historia clínica (aún no persistida).
     *
     * @param nroHistoria      Número único de historia.
     * @param grupoSanguineo   Grupo sanguíneo (Enum).
     * @param antecedentes     Antecedentes médicos.
     * @param medicacionActual Medicación actual.
     * @param observaciones    Observaciones generales.
     * @param fechaApertura    Fecha de creación.
     * @param pacienteId       ID del paciente asociado.
     */
    public HistoriaClinica(String nroHistoria, GrupoSanguineo grupoSanguineo, String antecedentes,
                           String medicacionActual, String observaciones, LocalDate fechaApertura, Long pacienteId) {
        super();
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
        this.fechaApertura = fechaApertura;
        this.pacienteId = pacienteId;
    }

    /**
     * Constructor completo para reconstruir objetos desde la base de datos.
     *
     * @param id               ID único de la historia.
     * @param eliminado        Estado de eliminación lógica.
     * @param nroHistoria      Número único de historia.
     * @param grupoSanguineo   Grupo sanguíneo.
     * @param antecedentes     Antecedentes médicos.
     * @param medicacionActual Medicación actual.
     * @param observaciones    Observaciones generales.
     * @param fechaApertura    Fecha de creación.
     * @param pacienteId       ID del paciente asociado.
     */
    public HistoriaClinica(Long id, boolean eliminado, String nroHistoria, GrupoSanguineo grupoSanguineo,
                           String antecedentes, String medicacionActual, String observaciones,
                           LocalDate fechaApertura, Long pacienteId) {
        super(id, eliminado);
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
        this.fechaApertura = fechaApertura;
        this.pacienteId = pacienteId;
    }

    /**
     * Obtiene el número único de identificación de la historia clínica.
     *
     * @return El número de historia.
     */
    public String getNroHistoria() {
        return nroHistoria;
    }

    /**
     * Establece el número único de la historia clínica.
     *
     * @param nroHistoria El nuevo número de historia.
     */
    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    /**
     * Obtiene el grupo sanguíneo del paciente.
     *
     * @return El grupo sanguíneo.
     */
    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    /**
     * Establece el grupo sanguíneo del paciente.
     *
     * @param grupoSanguineo El nuevo grupo sanguíneo.
     */
    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    /**
     * Obtiene los antecedentes médicos registrados.
     *
     * @return Texto con los antecedentes.
     */
    public String getAntecedentes() {
        return antecedentes;
    }

    /**
     * Establece los antecedentes médicos.
     *
     * @param antecedentes Texto descriptivo de antecedentes.
     */
    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    /**
     * Obtiene la medicación que el paciente está tomando actualmente.
     *
     * @return Texto con la medicación actual.
     */
    public String getMedicacionActual() {
        return medicacionActual;
    }

    /**
     * Establece la medicación actual del paciente.
     *
     * @param medicacionActual Texto descriptivo de la medicación.
     */
    public void setMedicacionActual(String medicacionActual) {
        this.medicacionActual = medicacionActual;
    }

    /**
     * Obtiene las observaciones generales de la historia clínica.
     *
     * @return Texto con observaciones.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece observaciones generales adicionales.
     *
     * @param observaciones Texto de observaciones.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Obtiene la fecha en la que se abrió o creó la historia clínica.
     *
     * @return La fecha de apertura.
     */
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Establece la fecha de apertura de la historia clínica.
     *
     * @param fechaApertura La nueva fecha de apertura.
     */
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Obtiene el ID del paciente propietario de esta historia clínica.
     *
     * @return El ID del paciente.
     */
    public Long getPacienteId() {
        return pacienteId;
    }

    /**
     * Establece el ID del paciente propietario.
     *
     * @param pacienteId El ID del paciente.
     */
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    /**
     * Genera un resumen breve de la Historia Clínica.
     * Útil para listados compactos o logs.
     *
     * @return Cadena con el número de historia y el grupo sanguíneo.
     */
    public String brief() {
        return "HC{nro='" + nroHistoria + "', grupo=" + (grupoSanguineo != null ? grupoSanguineo.db() : "-") + "}";
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "id=" + getId() +
                ", nroHistoria='" + nroHistoria + '\'' +
                ", grupo=" + (grupoSanguineo != null ? grupoSanguineo.db() : "null") +
                ", fechaApertura=" + fechaApertura +
                ", pacienteId=" + pacienteId +
                '}';
    }
}
