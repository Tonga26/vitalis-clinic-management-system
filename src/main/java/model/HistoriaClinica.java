package model;

import java.time.LocalDate;

/**
 * Entidad que representa la Historia Clínica de un paciente.
 * <p>
 * Contiene la información médica relevante como el grupo sanguíneo, antecedentes,
 * medicación actual y observaciones. Esta clase es la parte dependiente (Lado B)
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

        public String db() {
            return db;
        }

        public static GrupoSanguineo fromDb(String s) {
            if (s == null) return null;
            for (GrupoSanguineo g : values()) {
                if (g.db.equalsIgnoreCase(s)) return g;
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

    // Clave foránea para vincular con el Paciente en la BD
    private Long pacienteId;

    /**
     * Constructor por defecto.
     * Necesario para frameworks y creación paso a paso.
     */
    public HistoriaClinica() {
        super();
    }

    /**
     * Constructor para crear una NUEVA historia clínica (Aún no persistida).
     *
     * @param nroHistoria      Número de historia (código interno).
     * @param grupoSanguineo   Grupo sanguíneo del paciente.
     * @param antecedentes     Antecedentes médicos relevantes.
     * @param medicacionActual Medicación que toma actualmente.
     * @param observaciones    Notas adicionales.
     * @param fechaApertura    Fecha de creación de la ficha.
     * @param pacienteId       ID del paciente al que pertenece esta historia.
     */
    public HistoriaClinica(String nroHistoria, GrupoSanguineo grupoSanguineo, String antecedentes, String medicacionActual, String observaciones, LocalDate fechaApertura, Long pacienteId) {
        // Llama a super() implícitamente -> id=null, eliminado=false
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
        this.fechaApertura = fechaApertura;
        this.pacienteId = pacienteId;
    }

    /**
     * Constructor completo para reconstruir desde la Base de Datos.
     *
     * @param id               ID único de la historia.
     * @param eliminado        Estado de soft-delete.
     * @param nroHistoria      Número de historia.
     * @param grupoSanguineo   Grupo sanguíneo.
     * @param antecedentes     Antecedentes.
     * @param medicacionActual Medicación.
     * @param observaciones    Observaciones.
     * @param fechaApertura    Fecha de apertura.
     * @param pacienteId       ID del paciente dueño.
     */
    public HistoriaClinica(Long id, boolean eliminado, String nroHistoria, GrupoSanguineo grupoSanguineo, String antecedentes, String medicacionActual, String observaciones, LocalDate fechaApertura, Long pacienteId) {
        super(id, eliminado);
        this.nroHistoria = nroHistoria;
        this.grupoSanguineo = grupoSanguineo;
        this.antecedentes = antecedentes;
        this.medicacionActual = medicacionActual;
        this.observaciones = observaciones;
        this.fechaApertura = fechaApertura;
        this.pacienteId = pacienteId;
    }

    public String getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getMedicacionActual() {
        return medicacionActual;
    }

    public void setMedicacionActual(String medicacionActual) {
        this.medicacionActual = medicacionActual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    /**
     * Obtiene el ID del paciente asociado.
     * Fundamental para la persistencia en base de datos (Foreign Key).
     *
     * @return ID del paciente.
     */
    public Long getPacienteId() {
        return pacienteId;
    }

    /**
     * Establece el ID del paciente asociado.
     *
     * @param pacienteId Nuevo ID de paciente.
     */
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    /**
     * Genera un resumen breve de la Historia Clínica.
     *
     * @return Cadena con el número de historia y el grupo sanguíneo.
     */
    public String brief() {
        return "HC{nro='" + nroHistoria + "', grupo=" + (grupoSanguineo != null ? grupoSanguineo.db() : "-") + "}";
    }
}