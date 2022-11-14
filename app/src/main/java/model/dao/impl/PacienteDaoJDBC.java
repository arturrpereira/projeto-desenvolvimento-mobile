package model.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import db.database;
import model.dao.PacienteDao;
import model.entities.Medico;
import model.entities.Paciente;
import model.entities.Usuario;

public class PacienteDaoJDBC implements PacienteDao {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public PacienteDaoJDBC(Context context) {
        database db = new database(context);
        this.escreve = db.getWritableDatabase();
        this.le = db.getReadableDatabase();
    }

    @Override
    public void insert(Paciente obj) {

        ContentValues cv = new ContentValues();
        cv.put("fk_id_usuario", obj.getUsuario().getId_usuario());

        try{

            escreve.insert("Paciente", null, cv);
            Log.i("sucessodb", "Paciente salvo com sucesso!");

        }
        catch (Exception e){
            Log.i("errordb", "Erro ao salvar Usuario" + e.getMessage());
        }
    }

    @Override
    public Paciente findByUsuario(Usuario usuario) {

        Paciente paciente = new Paciente();
        Usuario user = new Usuario();
        String sql = "SELECT * FROM Paciente,Usuario WHERE fk_id_usuario = ? AND id_usuario = ?";

        String[] args = {String.valueOf(usuario.getId_usuario()), String.valueOf(usuario.getId_usuario())};
        Cursor c = le.rawQuery(sql, args);

        if(c.moveToNext()) {
            paciente.setId_paciente(c.getLong(0));

            usuario.setId_usuario(c.getLong(2));
            usuario.setEmail_usuario(c.getString(3));
            usuario.setSenha_usuario(c.getString(4));
            usuario.setNome_usuario(c.getString(5));
            usuario.setTelefone_usuario(c.getString(6));
            usuario.setData_nasc(c.getString(7));
            usuario.setCpf_usuario(c.getString(8));
            usuario.setTipo_usuario(c.getString(9));

            paciente.setUsuario(usuario);
        }

        return paciente;
    }
}
