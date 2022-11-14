package model.dao;

import model.entities.Medico;
import model.entities.Paciente;
import model.entities.Usuario;

public interface PacienteDao {

    void insert(Paciente obj);
    Paciente findByUsuario(Usuario usuario);
}
