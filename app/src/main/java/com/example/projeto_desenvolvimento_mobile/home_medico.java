package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import model.dao.impl.ConsultaDaoJDBC;
import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Paciente;

public class home_medico extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    Button timeButton;
    int hour, minute;

    private Medico entityMedico = new Medico();

    private TextView nome, email;

    private ListView listarPacientes;

    private List<Consulta> consultas;

    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_medico);
        getSupportActionBar().hide();
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton2);

        entityMedico = (Medico) getIntent().getSerializableExtra("medico");

        nome = findViewById(R.id.textNomeMedico);
        email = findViewById(R.id.textEmailMedico);
        uparInformacoes();

        listarPacientes = findViewById(R.id.listaPacientes);

        ConsultaDaoJDBC consultaService = new ConsultaDaoJDBC(getApplicationContext());
        consultas = consultaService.findByMedico(entityMedico);

        String[] stringsConsultas = new String[consultas.size()];

        int i = 0;

        for (Consulta consulta : consultas) {
            stringsConsultas[i] = "Data da consulta: " + consulta.getData_consulta() + " \nNome do paciente: " + consulta.getPaciente().getUsuario().getNome_usuario();
            i +=1;
        }

        // criação de Adapter
        ArrayAdapter<String> meuAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, stringsConsultas
        );

        listarPacientes = (ListView) findViewById(R.id.listaPacientes);

        //Atribuindo o meuAdapter à lista
        listarPacientes.setAdapter(meuAdapter);

        // criando uma ação : click
        listarPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // armazenar o valor do item selecionado
                String itemSelecionado = (String) listarPacientes.getItemAtPosition(i);

                // monta uam String para exibir na tela
                // String saida = "Doutor : '" + medicos.get(i).getUsuario().getNome_usuario() + "' escolhido para consulta";

                // preparar uma mensagem na tela
                //Toast.makeText(getApplicationContext(), saida, Toast.LENGTH_LONG).show();

                x = i;
            }
        });
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
        nome.setText(entityMedico.getUsuario().getNome_usuario().toString());
        email.setText(entityMedico.getUsuario().getEmail_usuario().toString());
    }
}