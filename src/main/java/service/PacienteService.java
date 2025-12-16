package service;

import model.Paciente;
import java.sql.SQLException;
import java.util.Optional;

public interface PacienteService extends GenericService<Paciente> {
    Optional<Paciente> findByDni(String dni) throws SQLException;
}
