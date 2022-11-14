package com.example.projeto_desenvolvimento_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import model.dao.impl.ConsultaDaoJDBC;
import model.dao.impl.MedicoDaoJDBC;
import model.entities.Consulta;
import model.entities.Medico;
import model.entities.Paciente;

public class agendamentoCliente extends AppCompatActivity {

    private ListView listaMedicos;


    private DatePickerDialog datePickerDialog;
    private Button dateButton, btnAgendamento;
    private int x;

    private Paciente entityPaciente = new Paciente();
    private List<Medico> medicos;
    private Consulta entityConsulta = new Consulta();


    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_cliente);
        initDatePicker();
        entityPaciente = (Paciente) getIntent().getSerializableExtra("paciente");
        dateButton = findViewById(R.id.datePickerButton);
        btnAgendamento = findViewById(R.id.btnAgendar);

        MedicoDaoJDBC medicoService = new MedicoDaoJDBC(getApplicationContext());
        medicos = medicoService.findAll();

        String[] stringsMedicos = new String[medicos.size()];

        int i = 0;

        for (Medico medico : medicos) {
            stringsMedicos[i] = "Doutor: " + medico.getUsuario().getNome_usuario() + " \nCRM: " + medico.getCrm_medico() + " \nValor da consulta: " + medico.getValor_consulta();
            i +=1;
        }

        // criação de Adapter
        ArrayAdapter<String> meuAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, stringsMedicos
        );

        listaMedicos = (ListView) findViewById(R.id.listaMedicos);

        //Atribuindo o meuAdapter à lista
        listaMedicos.setAdapter(meuAdapter);

        // criando uma ação : click
        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // armazenar o valor do item selecionado
                String itemSelecionado = (String) listaMedicos.getItemAtPosition(i);

                // monta uam String para exibir na tela
                String saida = "Doutor : '" + medicos.get(i).getUsuario().getNome_usuario() + "' escolhido para consulta";

                // preparar uma mensagem na tela
                Toast.makeText(getApplicationContext(), saida, Toast.LENGTH_LONG).show();

                x = i;
            }
        });

        btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entityConsulta.setData_consulta(dateButton.getText().toString());
                entityConsulta.setMedico(medicos.get(x));
                entityConsulta.setPaciente(entityPaciente);

                ConsultaDaoJDBC consultaService = new ConsultaDaoJDBC(getApplicationContext());
                try {
                    consultaService.insert(entityConsulta);

                    Toast.makeText(getApplicationContext(), "Consulta agendada com sucesso!", Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Erro ao agendar consulta: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
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



}