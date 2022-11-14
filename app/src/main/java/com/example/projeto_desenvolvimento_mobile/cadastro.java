package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.dao.impl.PacienteDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Paciente;
import model.entities.Usuario;

public class cadastro extends AppCompatActivity {

    Usuario entityUsuario = new Usuario();

    Paciente entityPaciente = new Paciente();

    private EditText email, senha, nome, telefone, data, CPF;

    public Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        nome = findViewById(R.id.nome);
        telefone = findViewById(R.id.telefone);
        data = findViewById(R.id.data);
        CPF = findViewById(R.id.cpf);


        btnCadastrar = findViewById(R.id.btnCadastroCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    entityUsuario = getFormData();
                    entityUsuario.setTipo_usuario("PACIENTE");

                    UsuarioDaoJDBC usuarioService = new UsuarioDaoJDBC(getApplicationContext());
                    entityUsuario = usuarioService.insert(entityUsuario);

                    entityPaciente.setUsuario(entityUsuario);

                    PacienteDaoJDBC pacienteService = new PacienteDaoJDBC(getApplicationContext());
                    pacienteService.insert(entityPaciente);

                    Toast.makeText(cadastro.this, "Paciente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(cadastro.this, "Erro ao cadastrar paciente!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Usuario getFormData() {
        Usuario obj = new Usuario();

        obj.setEmail_usuario(email.getText().toString());
        obj.setSenha_usuario(senha.getText().toString());
        obj.setNome_usuario(nome.getText().toString());
        obj.setTelefone_usuario(telefone.getText().toString());
        obj.setData_nasc(data.getText().toString());
        obj.setCpf_usuario(CPF.getText().toString());

        return obj;
    }
}


