package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable {

    private Long id_consulta;
    private String data_consulta;

    private Paciente paciente;
    private Medico medico;

    public Consulta() {

    }

    public Consulta(Long id_consulta, String data_consulta, Paciente paciente, Medico medico) {
        this.id_consulta = id_consulta;
        this.data_consulta = data_consulta;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(Long id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
