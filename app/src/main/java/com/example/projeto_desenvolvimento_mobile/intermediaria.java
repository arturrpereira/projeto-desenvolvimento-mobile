package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class intermediaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediaria);
    }

    public void abrirTelaCadastroMedico(View view) {
        startActivity(new Intent(this, cadastro_medico.class));
    }

    public void abrirTelaCadastroPaciente(View view) {
        startActivity(new Intent(this, cadastro.class));
    }
}