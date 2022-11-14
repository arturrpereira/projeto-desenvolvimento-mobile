package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private Long id_usuario;
    private String senha_usuario;
    private String nome_usuario;
    private String telefone_usuario;
    private String data_nasc;
    private String cpf_usuario;
    private String email_usuario;
    private String tipo_usuario;

    public Usuario() {

    }

    public Usuario(Long id_usuario, String senha_usuario, String nome_usuario, String telefone_usuario, String data_nasc, String cpf_usuario, String email_usuario, String tipo_usuario) {
        this.id_usuario = id_usuario;
        this.senha_usuario = senha_usuario;
        this.nome_usuario = nome_usuario;
        this.telefone_usuario = telefone_usuario;
        this.data_nasc = data_nasc;
        this.cpf_usuario = cpf_usuario;
        this.email_usuario = email_usuario;
        this.tipo_usuario = tipo_usuario;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getTelefone_usuario() {
        return telefone_usuario;
    }

    public void setTelefone_usuario(String telefone_usuario) {
        this.telefone_usuario = telefone_usuario;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
