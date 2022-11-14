package model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente implements Serializable {

    private Long id_paciente;
    private Usuario usuario;

    public Paciente() {

    }

    public Paciente(Long id_paciente, Usuario usuario) {
        this.id_paciente = id_paciente;
        this.usuario = usuario;
    }

    public Long getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Long id_paciente) {
        this.id_paciente = id_paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
