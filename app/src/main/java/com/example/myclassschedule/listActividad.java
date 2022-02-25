package com.example.myclassschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listActividad extends AppCompatActivity {


    RecyclerView listaActividad;
    List<datos_actividad> actividad;
    adapterActualizarActividad adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_actividad);


        listaActividad=findViewById(R.id.recyclerViewActualizaractividad);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listaActividad.setLayoutManager(new LinearLayoutManager(this));
        actividad=new ArrayList<>();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        adapter=new adapterActualizarActividad(actividad);
        listaActividad.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentasctualizarActividad(view);

            }
        });


        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id=idusuario().toString();
                actividad.removeAll(actividad);
                for (DataSnapshot dataSnapshot:
                    //Se ingresa la ruta para obtener los datos
                        snapshot.child(id+"/Actividades").getChildren()) {
                    datos_actividad dactividad=dataSnapshot.getValue(datos_actividad.class);
                    actividad.add(dactividad);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void intentasctualizarActividad(View view) {
        Intent intent=new Intent(this, actualizarActividad.class);
        String nombreCurso=actividad.get(listaActividad.getChildAdapterPosition(view)).getCurso().toString();
        String descripcion=actividad.get(listaActividad.getChildAdapterPosition(view)).getDescripcion().toString();
        String fecha=actividad.get(listaActividad.getChildAdapterPosition(view)).getFecha().toString();
        String Uid=actividad.get(listaActividad.getChildAdapterPosition(view)).getId();


        intent.putExtra("nombre", nombreCurso);
        intent.putExtra("descripcion",descripcion);
        intent.putExtra("dia", fecha);
        intent.putExtra("id", Uid);

        startActivity(intent);
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
}