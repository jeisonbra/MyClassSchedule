package com.example.myclassschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class opciones extends AppCompatActivity {

    Button  agregarcurso, actualizar,cursosEliminar, actividad,actualizarActividad, eliminarActividad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        eliminarActividad=findViewById(R.id.btnEliminarActividad);
        agregarcurso=findViewById(R.id.btnAgregaroCurso);
        actualizar=findViewById(R.id.btnActualizarCurso);
        cursosEliminar=findViewById(R.id.btnEliminarCurso);
        actividad=findViewById(R.id.btnAgregaroActividad);
        actualizarActividad=findViewById(R.id.btnActualizarActividad);



        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActualizar();
            }
        });

        actualizarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentActualizarActividad();

            }


        });

        eliminarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentEliminaractividad();
            }
        });


        agregarcurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentagregarCurso();

            }
        });

        cursosEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                intentcursosEliminar();
            }
        });

        actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intentActividad();
            }
        });
    }

    private void intentEliminaractividad() {
        Intent intent=new Intent(this, eliminarActividad.class);
        startActivity(intent);
    }

    private void intentActualizarActividad() {
        Intent intent=new Intent(this, listActividad.class);
        startActivity(intent);
    }

    public void intentatras() {
        Intent intent=new Intent(this,inicio.class);
        startActivity(intent);
    }

    public void intentagregarCurso() {
        Intent intent=new Intent(this,agregarCurso.class);
        startActivity(intent);
    }

    public void intentActualizar() {
        Intent intent=new Intent(this,listcursosactualizar.class);
        startActivity(intent);
    }

    public void intentcursosEliminar() {
        Intent intent=new Intent(this,cursosEliminar.class);
        startActivity(intent);
    }


    public void intentActividad(){

        Intent intent=new Intent(this, listagregarActividad.class);

        startActivity(intent);
    }


    private void getinfoFirebase(){

    }



}