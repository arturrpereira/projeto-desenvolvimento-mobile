package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Objects;

import model.dao.impl.MedicoDaoJDBC;
import model.dao.impl.PacienteDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Medico;
import model.entities.Paciente;
import model.entities.Usuario;

public class login extends AppCompatActivity {

    private Button btnCadastrar, btnLogar;
    private EditText textEmail, textSenha;

    private Usuario entityUsuario = new Usuario();
    private Paciente entityPaciente = new Paciente();
    private Medico entityMedico = new Medico();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        btnLogar = findViewById(R.id.btnLoginLogin);

        textEmail = findViewById(R.id.editTextLoginEmail);
        textSenha = findViewById(R.id.editTextLoginSenha);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String email = textEmail.getText().toString();
                    String senha = textSenha.getText().toString();

                    UsuarioDaoJDBC usuarioService = new UsuarioDaoJDBC(getApplicationContext());

                    entityUsuario = usuarioService.findByEmailAndSenha(email, senha);

                    String teste = entityUsuario.getTipo_usuario();
                    if(entityUsuario.getTipo_usuario() == null) {
                        Toast.makeText(login.this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();

                    }

                    if(Objects.equals(entityUsuario.getTipo_usuario(), "PACIENTE")) {
                        PacienteDaoJDBC pacienteService = new PacienteDaoJDBC(getApplicationContext());
                        entityPaciente = pacienteService.findByUsuario(entityUsuario);
                        entityPaciente.setUsuario(entityUsuario);
                        abrirTelaPaciente(view);
                    }

                    if(Objects.equals(entityUsuario.getTipo_usuario(), "MEDICO")) {
                        MedicoDaoJDBC medicoService = new MedicoDaoJDBC(getApplicationContext());
                        entityMedico = medicoService.findByUsuario(entityUsuario);
                        entityMedico.setUsuario(entityUsuario);
                        abrirTelaMedico(view);
                    }

                }
                catch (Exception e) {
                    Toast.makeText(login.this, "Erro ao buscar usuário!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void abrirTelaPaciente(View view) {
        Intent home = new Intent(this, home.class);
        home.putExtra("paciente", entityPaciente);
        startActivity(home);
    }

    public void abrirTelaMedico(View view) {
        Intent home_medico = new Intent(this, home_medico.class);
        home_medico.putExtra("medico", entityMedico);
        startActivity(home_medico);
    }
}