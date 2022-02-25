package com.example.myclassschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class actualizarActividad extends AppCompatActivity {


    EditText descripcion, fecha;
    EditText nombrecurso;
    Button actualizar, btnfecha;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String Uid="";
    private  int fdia,fmes,fano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_actividad);
        descripcion=findViewById(R.id.txtdescripcionactualizarActividad);
        fecha=findViewById(R.id.txtfechadeentregaActualizarActividad);
        nombrecurso=findViewById(R.id.txtnobrecursoactualizar);
        actualizar=findViewById(R.id.btnactualizar);
        btnfecha=findViewById(R.id.btnfechaActualizarActividad);

        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                fdia=c.get(Calendar.DAY_OF_MONTH);
                fmes=c.get(Calendar.MONTH);
                fano=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(actualizarActividad.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,fdia,fmes,fano);
                datePickerDialog.show();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String des=descripcion.getText().toString();
                String fe=fecha.getText().toString();
                String nom=nombrecurso.getText().toString();
                String id= Uid.toString();
                 if(des.equals("")||fe.equals("")|| nom.equals("")){
                    validar();
                }else{
                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                    datos_actividad d=new datos_actividad();
                    String email=obtenerCorreo();
                    d.setId(id);
                    d.setEmail(email.toString());
                    d.setCurso(nom);
                    d.setDescripcion(des);
                    d.setFecha(fe);
                    databaseReference.child(d.getEmail()).child("Actividades").child(id).setValue(d);
                    limpiar();
                    Toast.makeText(actualizarActividad.this, "Actividad actualizada con exito", Toast.LENGTH_SHORT).show();

                }

            }
        });


        recibirDatos();
        inicializarFirebase();

    }

    private void recibirDatos() {

        Bundle extras=getIntent().getExtras();
        String nombreCurso=extras.getString("nombre");
        String des=extras.getString("descripcion");
        String fecha_ =extras.getString("dia");
        String id=extras.getString("id");



        this.nombrecurso.setText(nombreCurso);
        this.descripcion.setText(des);
        this.fecha.setText(fecha_);
        this.Uid=id;


    }

    public void validar(){

        String fecha_=fecha.getText().toString();
        String descripcion_=descripcion.getText().toString();
        String nombre=nombrecurso.getText().toString();

        if(nombre.equals("")){
            nombrecurso.setError("Requerido");
        }
        if(fecha_.equals("")){
            fecha.setError("Requerido");
        }
        if(descripcion_.equals("")) {
            descripcion.setError("Requerido");
        }

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
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

    public void limpiar(){
        nombrecurso.setText("");
        descripcion.setText("");
        fecha.setText("");
    }

}