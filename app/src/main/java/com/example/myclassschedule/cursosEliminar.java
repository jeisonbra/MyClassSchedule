package com.example.myclassschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class cursosEliminar extends AppCompatActivity {

    RecyclerView listaCursos;
    List<curso> cursos;
    adaptereliminarcurso adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_eliminar);
        listaCursos=findViewById(R.id.recyclerViewEliminar);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listaCursos.setLayoutManager(new LinearLayoutManager(this));
        cursos=new ArrayList<>();

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        adapter=new adaptereliminarcurso(cursos);
        listaCursos.setAdapter(adapter);


        String id=idusuario().toString();


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alerta=new AlertDialog.Builder(cursosEliminar.this);
                alerta.setMessage("¿Desea eliminar este curso?").setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String idcurso=cursos.get(listaCursos.getChildAdapterPosition(view)).getId().toString();
                        FirebaseDatabase database= FirebaseDatabase.getInstance();
                        DatabaseReference ref= database.getReference(id).child("Cursos").child(idcurso);
                        ref.removeValue();
                        Toast.makeText(cursosEliminar.this, "Curso eliminado", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog titulo= alerta.create();
                titulo.setTitle("Eliminar curso");
                titulo.show();


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

       idusuario().toString();

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