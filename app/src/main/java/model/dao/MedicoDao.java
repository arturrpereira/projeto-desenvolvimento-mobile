package model.dao;

import java.util.List;

import model.entities.Medico;
import model.entities.Usuario;

public interface MedicoDao {

    void insert(Medico obj);
    List<Medico> findAll();
    Medico findByUsuario(Usuario usuario);
}
