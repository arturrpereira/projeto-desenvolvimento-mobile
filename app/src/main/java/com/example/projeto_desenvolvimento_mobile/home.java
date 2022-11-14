package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.dao.impl.ConsultaDaoJDBC;
import model.dao.impl.MedicoDaoJDBC;
import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Paciente;

public class home extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    Button timeButton;
    int hour, minute;

    private TextView nome, email;

    private Paciente entityPaciente = new Paciente();

    private List<Consulta> consultas = new ArrayList<>();

    private ListView listarMedicos;

    private int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        initDatePicker();

        entityPaciente = (Paciente) getIntent().getSerializableExtra("paciente");
        dateButton = findViewById(R.id.datePickerButton3);
        dateButton = findViewById(R.id.datePickerButton);
        //btnAgendamento = findViewById(R.id.btnAgendar);

        listarMedicos = findViewById(R.id.listaConsultas);
        nome = findViewById(R.id.textNome);
        email = findViewById(R.id.textEmail);
        uparInformacoes();

        ConsultaDaoJDBC consultaService = new ConsultaDaoJDBC(getApplicationContext());
        consultas = consultaService.findByPaciente(entityPaciente);

        String[] stringsConsultas = new String[consultas.size()];

        int i = 0;

        for (Consulta consulta : consultas) {
            stringsConsultas[i] = "Data da consulta: " + consulta.getData_consulta() + " \nMédico: " + consulta.getMedico().getUsuario().getNome_usuario();
            i +=1;
        }

        // criação de Adapter
        ArrayAdapter<String> meuAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, stringsConsultas
        );

        listarMedicos = (ListView) findViewById(R.id.listaConsultas);

        //Atribuindo o meuAdapter à lista
        listarMedicos.setAdapter(meuAdapter);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void abrirTelaAgendamento(View view) {
        Intent home = new Intent(this, agendamentoCliente.class);
        home.putExtra("paciente", entityPaciente);
        startActivity(home);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEV";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAI";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SET";
        if(month == 10)
            return "OUT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEZ";

        return "JAN";
    }
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void uparInformacoes() {
        nome.setText(entityPaciente.getUsuario().getNome_usuario().toString());
        email.setText(entityPaciente.getUsuario().getEmail_usuario().toString());
    }
}