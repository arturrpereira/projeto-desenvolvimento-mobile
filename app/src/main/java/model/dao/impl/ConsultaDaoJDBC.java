package model.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import db.database;
import model.dao.ConsultaDao;
import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Paciente;
import model.entities.Usuario;

public class ConsultaDaoJDBC implements ConsultaDao {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ConsultaDaoJDBC(Context context) {
        database db = new database(context);
        this.escreve = db.getWritableDatabase();
        this.le = db.getReadableDatabase();
    }

    @Override
    public void insert(Consulta obj) {

        ContentValues cv = new ContentValues();
        cv.put("data_consulta", obj.getData_consulta());

        cv.put("fk_id_paciente", obj.getPaciente().getId_paciente());
        cv.put("fk_id_medico", obj.getMedico().getId_medico());

        try{
            escreve.insert("Consulta", null, cv);
            Log.i("sucesso-db", "Consulta salva com sucesso!");
        }
        catch (Exception e){
            Log.i("error-db", "Erro ao salvar Consulta" + e.getMessage());
        }
    }

    @Override
    public List<Consulta> findByPaciente(Paciente paciente) {

        List<Consulta> consultas = new ArrayList<>();

        String sql = "SELECT * FROM Consulta WHERE fk_id_paciente = ?";

        String[] args = {String.valueOf(paciente.getId_paciente())};
        Cursor c = le.rawQuery(sql, args);

        while (c.moveToNext()){
            Consulta consulta = new Consulta();
            Medico medico = new Medico();
            Usuario user = new Usuario();

            consulta.setMedico(medico);
            consulta.getMedico().setUsuario(user);

            consulta.setId_consulta(c.getLong(0));
            consulta.setData_consulta(c.getString(1));

            consulta.getMedico().setId_medico(c.getLong(3));

            String sql1 = "SELECT * FROM Medico WHERE id_medico = ?";

            String[] args1 = {String.valueOf(consulta.getMedico().getId_medico())};
            Cursor c1 = le.rawQuery(sql1, args1);

            if (c1.moveToNext()) {
                consulta.getMedico().getUsuario().setId_usuario(c1.getLong(3));
            }

            String sql2 = "SELECT * FROM Usuario WHERE id_usuario = ?";

            String[] args2 = {String.valueOf(consulta.getMedico().getUsuario().getId_usuario())};
            Cursor c2 = le.rawQuery(sql2, args2);

            if (c2.moveToNext()) {
                consulta.getMedico().getUsuario().setNome_usuario(c2.getString(3));
            }

            consultas.add(consulta);
        }

        return consultas;
    }

    @Override
    public List<Consulta> findByMedico(Medico medico) {

        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE fk_id_medico = ?";

        String[] args = {String.valueOf(medico.getId_medico())};
        Cursor c = le.rawQuery(sql, args);

        while (c.moveToNext()){
            Consulta consulta = new Consulta();
            Usuario user = new Usuario();
            Paciente paciente = new Paciente();

            consulta.setPaciente(paciente);
            consulta.getPaciente().setUsuario(user);

            consulta.setId_consulta(c.getLong(0));
            consulta.setData_consulta(c.getString(1));

            consulta.getPaciente().setId_paciente(c.getLong(2));

            String sql1 = "SELECT * FROM Paciente WHERE id_paciente = ?";

            String[] args1 = {String.valueOf(consulta.getPaciente().getId_paciente())};
            Cursor c1 = le.rawQuery(sql1, args1);

            if (c1.moveToNext()) {
                consulta.getPaciente().getUsuario().setId_usuario(c1.getLong(1));
            }

            String sql2 = "SELECT * FROM Usuario WHERE id_usuario = ?";

            String[] args2 = {String.valueOf(consulta.getPaciente().getUsuario().getId_usuario())};
            Cursor c2 = le.rawQuery(sql2, args2);

            if (c2.moveToNext()) {
                consulta.getPaciente().getUsuario().setNome_usuario(c2.getString(3));
            }

            consultas.add(consulta);
        }

        return consultas;
    }
}