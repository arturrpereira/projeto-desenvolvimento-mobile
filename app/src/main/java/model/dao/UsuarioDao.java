package model.dao;

import model.entities.Usuario;

public interface UsuarioDao {

    Usuario insert(Usuario obj);
    Usuario findByEmailAndSenha(String email, String senha);
}
