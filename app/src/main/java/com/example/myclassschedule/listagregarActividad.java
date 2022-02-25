package com.example.myclassschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listagregarActividad extends AppCompatActivity {



    RecyclerView listaActividad;
    List<curso> cursos;
    adapteragregaractividad adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagregar_actividad);

        listaActividad=findViewById(R.id.recyclerViewagregaractividad);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listaActividad.setLayoutManager(new LinearLayoutManager(this));
        cursos=new ArrayList<>();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        adapter=new adapteragregaractividad(cursos);


        listaActividad.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentasctualizar(view);
            }
        });
        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id=idusuario().toString();
                cursos.removeAll(cursos);
                for (DataSnapshot dataSnapshot:
                    //Se ingresa la ruta para obtener los datos
                        snapshot.child(id+"/Cursos").getChildren()) {
                    curso curso=dataSnapshot.getValue(curso.class);
                    cursos.add(curso);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public String idusuario(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail="";
        if (user != null) {
            userEmail = user.getUid();
        } else {
            // No user is signed in
        }
        return userEmail;
    }

    private void intentasctualizar(View view) {

        Intent intent=new Intent(this, agregarActividad.class);
        String nombre=cursos.get(listaActividad.getChildAdapterPosition(view)).getNombre().toString();

        intent.putExtra("nombre", nombre);

        startActivity(intent);
    }
}