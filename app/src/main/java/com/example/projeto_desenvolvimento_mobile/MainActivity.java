package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCadastrar, btnLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnLogar = findViewById(R.id.btnLogar);

    }

    public void abrirTelaIntermediaria(View view) {
        startActivity(new Intent(this, intermediaria.class));
    }
    public void abrirTelaLogin(View view) {
        startActivity(new Intent(this, login.class));
    }
}