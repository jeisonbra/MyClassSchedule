package com.example.myclassschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Actualizar extends AppCompatActivity {



    Button actualizar, btnhora;
    EditText nombre,grupo, dia, hora, aula,docente;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String Uid="";
    private  int hh,mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        nombre=findViewById(R.id.txtnombreActualizar);
        grupo=findViewById(R.id.txtgrupoActualizar);
        dia=findViewById(R.id.txtdiaActualizar);
        hora=findViewById(R.id.txthoraActualizar);
        aula=findViewById(R.id.txtaulaActualizar);
        docente=findViewById(R.id.txtdocenteActualizar);
        actualizar=findViewById(R.id.btnactualizar);
        btnhora=findViewById(R.id.btnfechactualizarcurso);
        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                hh=c.get(Calendar.HOUR_OF_DAY);
                mm=c.get(Calendar.MINUTE);
                if(mm<10){

                }

                TimePickerDialog timePickerDialog = new TimePickerDialog(Actualizar.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora.setText(hourOfDay+":"+minute);
                    }
                },hh,mm,false);
                timePickerDialog.show();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nombre_=nombre.getText().toString();
                String grupo_=grupo.getText().toString();
                String dia_=dia.getText().toString();
                String hora_=hora.getText().toString();
                String aula_=aula.getText().toString();
                String docente_=docente.getText().toString();
                String id=Uid.toString();

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
                    Toast.makeText(Actualizar.this, "Curso actualizado con exito", Toast.LENGTH_SHORT).show();

                }

            }
        });
        inicializarFirebase();
        recibirDatos();
    }

    private void recibirDatos() {

        Bundle extras=getIntent().getExtras();

        String nombre=extras.getString("nombre");
        String grupo =extras.getString("grupo");
        String dia=extras.getString("dia");
        String hora =extras.getString("hora");
        String aula=extras.getString("aula");
        String docente =extras.getString("docente");

        this.nombre.setText(nombre);
        this.grupo.setText(grupo);
        this.dia.setText(dia);
        this.hora.setText(hora);
        this.aula.setText(aula);
        this.docente.setText(docente);
        this.Uid=extras.getString("id").toString();

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


}