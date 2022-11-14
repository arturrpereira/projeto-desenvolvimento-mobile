package model.dao.impl;

import android.app.Application;
import android.app.usage.NetworkStatsManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projeto_desenvolvimento_mobile.cadastro;

import db.database;
import model.dao.UsuarioDao;
import model.entities.Medico;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public UsuarioDaoJDBC(Context context) {
        database db = new database(context);
        this.escreve = db.getWritableDatabase();
        this.le = db.getReadableDatabase();
    }

    @Override
    public Usuario insert(Usuario obj) {

        ContentValues cv = new ContentValues();
        cv.put("email_usuario", obj.getEmail_usuario());
        cv.put("senha_usuario", obj.getSenha_usuario());
        cv.put("nome_usuario", obj.getNome_usuario());
        cv.put("telefone_usuario", obj.getTelefone_usuario());
        cv.put("data_nasc", obj.getData_nasc());
        cv.put("cpf_usuario", obj.getCpf_usuario());
        cv.put("tipo_usuario", obj.getTipo_usuario());

        try{
            //escreve.execSQL(sql);
            obj.setId_usuario(escreve.insert("Usuario", null, cv));
            Log.i("sucessodb", "Usuario salvo com sucesso!");

            return obj;
        }
        catch (Exception e){
            Log.i("errordb", "Erro ao salvar Usuario" + e.getMessage());
        }

        return null;
    }

    @Override
    public Usuario findByEmailAndSenha(String email, String senha) {

        Usuario usuario = new Usuario();

        try{
            String sql = "SELECT * FROM Usuario WHERE email_usuario = ? AND senha_usuario = ?";

            String[] args = {email, senha};
            Cursor c = le.rawQuery(sql, args);

            if(c.moveToNext()) {
                usuario.setId_usuario(c.getLong(0));
                usuario.setSenha_usuario(c.getString(1));
                usuario.setNome_usuario(c.getString(2));
                usuario.setTelefone_usuario(c.getString(3));
                usuario.setData_nasc(c.getString(4));
                usuario.setCpf_usuario(c.getString(5));
                usuario.setEmail_usuario(c.getString(6));
                usuario.setTipo_usuario(c.getString(7));
            }

            Log.i("sucessodb", "Usuario encontrado com sucesso!");
            return usuario;
        }
        catch (Exception e) {
            Log.i("errordb", "Erro ao encontrar Usuario" + e.getMessage());
        }

        return usuario;
    }
}
