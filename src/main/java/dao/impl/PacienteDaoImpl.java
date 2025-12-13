package dao.impl;

import dao.PacienteDao;
import model.HistoriaClinica;
import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDaoImpl implements PacienteDao {

    private final Connection conn;

    public PacienteDaoImpl(Connection conn) {
        this.conn = conn;
    }

    private Paciente map(ResultSet rs) throws SQLException{
        Paciente p = new Paciente();
        p.setId(rs.getLong("id"));
        p.setEliminado(rs.getBoolean("eliminado"));
        p.setDni(rs.getString("dni"));
        p.setNombre(rs.getString("nombre"));
        p.setApellido(rs.getString("apellido"));
        java.sql.Date f = rs.getDate("fecha_nacimiento");
        p.setFechaNacimiento(f != null ? f.toLocalDate() : null);

        long hcId =rs.getLong("hc_id");

        if (hcId > 0){
            HistoriaClinica h =new HistoriaClinica();
            h.setId(hcId);
            h.setEliminado(rs.getBoolean("hc_eliminado"));
            h.setNroHistoria(rs.getString("nro_historia"));
            String gs = rs.getString("grupo_sanguineo");
            h.setGrupoSanguineo(HistoriaClinica.GrupoSanguineo.fromDb(gs));
            h.setAntecedentes(rs.getString("antecedentes"));
            h.setMedicacionActual(rs.getString("medicacion_actual"));
            h.setObservaciones(rs.getString("observaciones"));
            java.sql.Date fa = rs.getDate("hc_fecha_apertura");
            h.setFechaApertura(fa != null ? fa.toLocalDate() : null);

            p.setHistoriaClinica(h); // asigna la historia clinica al paciente
        }

        return p;

    }

    @Override
    public Paciente create(Paciente p) throws SQLException {
        String sql = "INSERT INTO paciente (eliminado, dni, nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setBoolean(1, p.isEliminado());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getApellido());
            if (p.getFechaNacimiento() != null){
                ps.setDate(5, java.sql.Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    p.setId(rs.getLong(1));
                }
            }

        }
        return p;
    }

    @Override
    public Optional<Paciente> findById(Long id) throws SQLException {
        String sql = "SELECT p.*, " +
                "hc.id AS hc_id, hc.eliminado AS hc_eliminado, hc.nro_historia, " +
                "hc.grupo_sanguineo, hc.antecedentes, hc.medicacion_actual, " +
                "hc.observaciones, " +
                "hc.fecha_apertura AS hc_fecha_apertura " +
                "FROM paciente p " +
                "LEFT JOIN historia_clinica hc ON p.id = hc.paciente_id AND hc.eliminado = 0 " +
                "WHERE p.id = ? AND p.eliminado = 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Paciente> getAll() throws SQLException {
        String sql = "SELECT p.*, " +
                "hc.id AS hc_id, hc.eliminado AS hc_eliminado, hc.nro_historia, " +
                "hc.grupo_sanguineo, hc.antecedentes, hc.medicacion_actual, " +
                "hc.observaciones, " +
                "hc.fecha_apertura AS hc_fecha_apertura " +
                "FROM paciente p " +
                "LEFT JOIN historia_clinica hc ON p.id = hc.paciente_id AND hc.eliminado = 0 " +
                "WHERE p.eliminado = 0";

        List<Paciente> pacientesEncontrados = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Paciente p = map(rs);
                pacientesEncontrados.add(p);
            }
        }
        return pacientesEncontrados;
    }

    @Override
    public void update(Paciente p) throws SQLException {
        String sql = "UPDATE paciente SET eliminado=?, dni=?, nombre=?, apellido=?, fecha_nacimiento=? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setBoolean(1, p.isEliminado());
            ps.setString(2, p.getDni());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getApellido());
            if (p.getFechaNacimiento() != null){
                ps.setDate(5, java.sql.Date.valueOf(p.getFechaNacimiento()));
            } else {
                ps.setNull(5, Types.DATE);
            }
            ps.setLong(6, p.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "UPDATE paciente SET eliminado=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, 1);
            ps.setLong(2, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Paciente> findByDni(String dni) throws SQLException {
        String sql = "SELECT p.*, " +
                "hc.id AS hc_id, hc.eliminado AS hc_eliminado, hc.nro_historia, " +
                "hc.grupo_sanguineo, hc.antecedentes, hc.medicacion_actual, " +
                "hc.observaciones, " +
                "hc.fecha_apertura AS hc_fecha_apertura " +
                "FROM paciente p " +
                "LEFT JOIN historia_clinica hc ON p.id = hc.paciente_id AND hc.eliminado = 0 " +
                "WHERE p.dni = ? AND p.eliminado = 0";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }
}
