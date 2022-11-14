package model.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Medico implements Serializable {

    private Long id_medico;
    private String crm_medico;
    private float valor_consulta;

    private Usuario usuario;

    public Medico() {

    }

    public Medico(Long id_medico, String crm_medico, float valor_consulta, Usuario usuario) {
        this.id_medico = id_medico;
        this.crm_medico = crm_medico;
        this.valor_consulta = valor_consulta;
        this.usuario = usuario;
    }

    public Long getId_medico() {
        return id_medico;
    }

    public void setId_medico(Long id_medico) {
        this.id_medico = id_medico;
    }

    public String getCrm_medico() {
        return crm_medico;
    }

    public void setCrm_medico(String crm_medico) {
        this.crm_medico = crm_medico;
    }

    public float getValor_consulta() {
        return valor_consulta;
    }

    public void setValor_consulta(float valor_consulta) {
        this.valor_consulta = valor_consulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
