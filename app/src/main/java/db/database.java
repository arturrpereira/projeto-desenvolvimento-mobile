package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.Connection;

import model.entities.Consulta;

public class database extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "db_mobile";

    public database(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABELA_CONSULTA = "CREATE TABLE IF NOT EXISTS Consulta ("
                + "id_consulta INTEGER PRIMARY KEY, "
                + "data_consulta VARCHAR(45) NOT NULL, "
                + "fk_id_paciente  INT NOT NULL, "
                + "fk_id_medico INT NOT NULL, "
                + "CONSTRAINT  fk_Consulta_Paciente "
                + "FOREIGN KEY ( fk_id_paciente ) "
                + "REFERENCES Paciente  ( id_paciente ),"
                + "CONSTRAINT  fk_Consulta_Medico "
                + "FOREIGN KEY ( fk_id_medico ) "
                + "REFERENCES Paciente  ( id_medico ))";

        String TABELA_MEDICO = "CREATE TABLE IF NOT EXISTS Medico ("
                + "id_medico INTEGER PRIMARY KEY, "
                + "crm_medico VARCHAR(45) NOT NULL, "
                + "valor_da_consulta VARCHAR(45) NOT NULL, "
                + "fk_id_usuario INT NOT NULL, "
                + "CONSTRAINT  fk_Medico_Usuario "
                + "FOREIGN KEY ( fk_id_usuario ) "
                + "REFERENCES Usuario  ( id_usuario ))";

        String TABELA_PACIENTE = "CREATE TABLE IF NOT EXISTS Paciente ("
                + "id_paciente INTEGER PRIMARY KEY, "
                + "fk_id_usuario  INT NOT NULL, "
                + "CONSTRAINT  fk_Paciente_Usuario "
                + "FOREIGN KEY ( fk_id_usuario ) "
                + "REFERENCES Usuario  ( id_usuario ))";

        String TABELA_USUARIO = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "id_usuario INTEGER PRIMARY KEY, "
                + "email_usuario  VARCHAR(45) NOT NULL, "
                + "senha_usuario  VARCHAR(45) NOT NULL, "
                + "nome_usuario  VARCHAR(45) NOT NULL, "
                + "telefone_usuario  VARCHAR(45) NOT NULL, "
                + "data_nasc  VARCHAR(45) NOT NULL, "
                + "cpf_usuario  VARCHAR(45) NOT NULL, "
                + "tipo_usuario  VARCHAR(45) NOT NULL) ";

        db.execSQL(TABELA_CONSULTA);
        db.execSQL(TABELA_MEDICO);
        db.execSQL(TABELA_PACIENTE);
        db.execSQL(TABELA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
