package com.example.myclassschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.UUID;

public class agregarActividad extends AppCompatActivity {


    TextView nombre;
    EditText descripcion,fecha;
    Button agregarActividad, btfecha;
    private  int fdia,fmes,fano;



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actividad);

        agregarActividad=findViewById(R.id.btnactualizar);
        descripcion=findViewById(R.id.txtdescripcionactividad);
        fecha=findViewById(R.id.txtfecha);
        nombre=findViewById(R.id.nombreAgregarActividad);
        btfecha=findViewById(R.id.btnfecha);

        btfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c= Calendar.getInstance();
                fdia=c.get(Calendar.DAY_OF_MONTH);
                fmes=c.get(Calendar.MONTH);
                fano=c.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(agregarActividad.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }
                        ,fdia,fmes,fano);
                datePickerDialog.show();
            }
        });

        recibirDatos();

        agregarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             String des=descripcion.getText().toString();
             String fe=fecha.getText().toString();
             String nom=nombre.getText().toString();
             String id=UUID.randomUUID().toString();

                if(des.equals("")||fe.equals("")||nom.equals("")){
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
                    Toast.makeText(agregarActividad.this, "Actividad agregada con exito", Toast.LENGTH_SHORT).show();

                }

            }
        });


        inicializarFirebase();
    }

    private void recibirDatos() {

        Bundle extras=getIntent().getExtras();

        String nombre=extras.getString("nombre");


        this.nombre.setText(nombre);


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
    }


    public void validar(){

        String fecha_=fecha.getText().toString();
        String descripcion_=descripcion.getText().toString();


        if(fecha_.equals("")){
            fecha.setError("Requerido");
        }
        if(descripcion_.equals("")) {
            descripcion.setError("Requerido");
        }

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
        nombre.setText("");
        descripcion.setText("");
        fecha.setText("");
    }
}