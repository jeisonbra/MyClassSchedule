package com.example.myclassschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class agregarCurso extends AppCompatActivity {


    Button  agregar, btnhora;
    EditText nombre, grupo, dia, hora,aula, docente;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private  int hh,mm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        agregar=findViewById(R.id.btnAgregaroCurso);
        nombre=findViewById(R.id.txtnombreActualizar);
        grupo=findViewById(R.id.txtgrupoActualizar);
        dia=findViewById(R.id.txtdiaActualizar);
        hora=findViewById(R.id.txthoraActualizar);
        aula=findViewById(R.id.txtaulaActualizar);
        docente=findViewById(R.id.txtdocenteActualizar);
        btnhora=findViewById(R.id.btnHora);

        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                hh=c.get(Calendar.HOUR_OF_DAY);
                mm=c.get(Calendar.MINUTE);
                if(mm<10){

                }

                TimePickerDialog timePickerDialog = new TimePickerDialog(agregarCurso.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora.setText(hourOfDay+":"+minute);
                    }
                },hh,mm,false);
                timePickerDialog.show();
            }
        });



        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre_=nombre.getText().toString();
                String grupo_=grupo.getText().toString();
                String dia_=dia.getText().toString();
                String hora_=hora.getText().toString();
                String aula_=aula.getText().toString();
                String docente_=docente.getText().toString();
                String id= UUID.randomUUID().toString();


                if(nombre_.equals("")||grupo_.equals("")||dia_.equals("")||hora_.equals("")||aula_.equals("")||docente_.equals("")){
                    validar();
                }else{
                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                    String email=obtenerCorreo();
                    curso c=new curso();
                    c.setId(id);
                    c.setEmail(email.toString());
                    c.setNombre(nombre_);
                    c.setGrupo(grupo_);
                    c.setDia(dia_);
                    c.setHora(hora_);
                    c.setAula(aula_);
                    c.setDocente(docente_);
                    databaseReference.child(c.getEmail()).child("Cursos").child(id).setValue(c);
                    limpiar();
                    Toast.makeText(agregarCurso.this, "Curso agregado con exito", Toast.LENGTH_SHORT).show();

                }


            }
        });



        inicializarFirebase();
    }


    public String obtenerCorreo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail="";
        if (user != null) {
             userEmail = user.getUid();
        } else {
            // No user is signed in
        }
        return userEmail;
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }

    public void limpiar(){
        nombre.setText("");
        grupo.setText("");
        dia.setText("");
        hora.setText("");
        aula.setText("");
        docente.setText("");
    }

    public void validar(){

        String nombre_=nombre.getText().toString();
        String grupo_=grupo.getText().toString();
        String dia_=dia.getText().toString();
        String hora_=hora.getText().toString();
        String aula_=aula.getText().toString();
        String docente_=docente.getText().toString();

        if(nombre_.equals("")){
            nombre.setError("Requerido");
        }
        if(grupo_.equals("")){
            grupo.setError("Requerido");
        }
        if(dia_.equals("")){
            dia.setError("Requerido");
        } if(hora_.equals("")){
            hora.setError("Requerido");
        } if(aula_.equals("")){
            aula.setError("Requerido");
        } if(docente_.equals("")){
            docente.setError("Requerido");
        }

    }

    public void intent() {
        Intent intent=new Intent(this,opciones.class);
        startActivity(intent);
    }
}