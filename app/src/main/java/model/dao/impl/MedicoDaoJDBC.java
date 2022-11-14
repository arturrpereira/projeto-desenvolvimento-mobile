package model.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import db.database;
import model.dao.MedicoDao;
import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Usuario;

public class MedicoDaoJDBC implements MedicoDao {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public MedicoDaoJDBC(Context context) {
        database db = new database(context);
        this.escreve = db.getWritableDatabase();
        this.le = db.getReadableDatabase();
    }

    @Override
    public void insert(Medico obj) {

        ContentValues cv = new ContentValues();
        cv.put("crm_medico", obj.getCrm_medico());
        cv.put("valor_da_consulta", obj.getValor_consulta());
        cv.put("fk_id_usuario", obj.getUsuario().getId_usuario());

        try{
            escreve.insert("Medico", null, cv);
            Log.i("sucesso-db", "Médico salvo com sucesso!");
        }
        catch (Exception e){
            Log.i("error-db", "Erro ao salvar Medico" + e.getMessage());
        }
    }

    @Override
    public List<Medico> findAll() {

        List<Medico> medicos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Medico,Usuario WHERE fk_id_usuario = id_usuario";
            String[] args = {};
            Cursor c = le.rawQuery(sql, args);

            while (c.moveToNext()){
                Medico medico = new Medico();

                medico.setId_medico(c.getLong(0));
                medico.setCrm_medico(c.getString(1));
                medico.setValor_consulta(c.getFloat(2));

                Usuario usuario = new Usuario();
                usuario.setId_usuario(c.getLong(4));
                usuario.setEmail_usuario(c.getString(5));
                usuario.setSenha_usuario(c.getString(6));
                usuario.setNome_usuario(c.getString(7));
                usuario.setTelefone_usuario(c.getString(8));
                usuario.setData_nasc(c.getString(8));
                usuario.setCpf_usuario(c.getString(8));
                usuario.setTipo_usuario(c.getString(8));

                medico.setUsuario(usuario);

                medicos.add(medico);

                Log.i("sucessodb", "Medicos encontrados com sucesso");
            }

            return medicos ;
        }
        catch (Exception e) {

        }
        return null;

    }

    @Override
    public Medico findByUsuario(Usuario usuario) {

        Medico medico = new Medico();
        String sql = "SELECT * FROM Medico WHERE fk_id_usuario = ?";

        String[] args = {String.valueOf(usuario.getId_usuario())};
        Cursor c = le.rawQuery(sql, args);

        if(c.moveToNext()) {
            medico.setId_medico(c.getLong(0));
            medico.setCrm_medico(c.getString(1));
            medico.setValor_consulta(c.getFloat(2));
        }
        return medico;
    }
}
