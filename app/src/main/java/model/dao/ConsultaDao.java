package model.dao;

import java.util.List;

import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Paciente;

public interface ConsultaDao {

    void insert(Consulta obj);
    List<Consulta> findByPaciente(Paciente paciente);
    List<Consulta> findByMedico(Medico paciente);
}
