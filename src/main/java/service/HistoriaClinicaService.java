package service;

import model.HistoriaClinica;
import java.sql.SQLException;
import java.util.Optional;

public interface HistoriaClinicaService extends GenericService<HistoriaClinica> {
    Optional<HistoriaClinica> findByPacienteId(Long pacienteId) throws SQLException;
    void deleteByPacienteId(Long pacienteId) throws SQLException;
}
