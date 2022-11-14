package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto_desenvolvimento_mobile.R;

import model.dao.impl.MedicoDaoJDBC;
import model.dao.impl.PacienteDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;
import model.entities.Medico;
import model.entities.Paciente;
import model.entities.Usuario;

public class cadastro_medico extends AppCompatActivity {

    Usuario entityUsuario = new Usuario();

    Medico entityMedico = new Medico();

    private EditText email, senha, nome, telefone, data, CPF, CRM, valor;

    public Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_medico);
        getSupportActionBar().hide();

        email = findViewById(R.id.medicoEmail);
        senha = findViewById(R.id.medicoSenha);
        nome = findViewById(R.id.medicoNome);
        telefone = findViewById(R.id.medicoTelefone);
        data = findViewById(R.id.medicoData);
        CPF = findViewById(R.id.medicoCpf);
        CRM = findViewById(R.id.medicoCrm);
        valor = findViewById(R.id.medicoValorConsulta);

        btnCadastrar = findViewById(R.id.btnMedicoCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    entityUsuario = getFormData();
                    entityUsuario.setTipo_usuario("MEDICO");

                    UsuarioDaoJDBC usuarioService = new UsuarioDaoJDBC(getApplicationContext());
                    entityUsuario = usuarioService.insert(entityUsuario);

                    entityMedico.setCrm_medico(CRM.getText().toString());
                    entityMedico.setValor_consulta(Float.parseFloat(valor.getText().toString()));
                    entityMedico.setUsuario(entityUsuario);

                    MedicoDaoJDBC medicoService = new MedicoDaoJDBC(getApplicationContext());
                    medicoService.insert(entityMedico);

                    Toast.makeText(cadastro_medico.this, "Medico cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(cadastro_medico.this, "Erro ao cadastrar medico!" + e.getMessage(), Toast.LENGTH_SHORT).show();
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