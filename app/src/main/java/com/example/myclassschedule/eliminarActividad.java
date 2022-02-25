package com.example.myclassschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class eliminarActividad extends AppCompatActivity {


    RecyclerView listaActividad;
    List<datos_actividad> actividad;
    adapterActualizarActividad adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_actividad);

        listaActividad=findViewById(R.id.recyclerViewEliminaractividad);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listaActividad.setLayoutManager(new LinearLayoutManager(this));
        actividad=new ArrayList<>();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        adapter=new adapterActualizarActividad(actividad);
        listaActividad.setAdapter(adapter);

        String id=idusuario().toString();

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alerta=new AlertDialog.Builder(eliminarActividad.this);
                alerta.setMessage("¿Desea eliminar esta actividad?").setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String idactividad=actividad.get(listaActividad.getChildAdapterPosition(view)).getId().toString();
                                FirebaseDatabase database= FirebaseDatabase.getInstance();
                                DatabaseReference ref= database.getReference(id).child("Actividades").child(idactividad);
                                ref.removeValue();
                                Toast.makeText(eliminarActividad.this, "Actividad eliminada", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar actividad");
                titulo.show();

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