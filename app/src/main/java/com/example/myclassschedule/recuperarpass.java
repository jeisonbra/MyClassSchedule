package com.example.myclassschedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recuperarpass extends AppCompatActivity {


    EditText correo;
    Button enviar;
    String email="";


    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperarpass);

        correo=findViewById(R.id.txtemailrestablecerpass);
        enviar=findViewById(R.id.btnrecuperar);

        mAuth = FirebaseAuth.getInstance();
        mDialog= new ProgressDialog(this);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email=correo.getText().toString();
                if(!email.isEmpty()){
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();

                    recuperarPassword();
                    correo.setText("");

                }else{
                    Toast.makeText(recuperarpass.this, "Debe ingresar un correo.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recuperarPassword() {


            mAuth.setLanguageCode("es");
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(recuperarpass.this, "Se ha enviado un correo para restablecimiento de contraseña.", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(recuperarpass.this, "No se pudo enviar el correo para restablecimiento de contraseña.", Toast.LENGTH_LONG).show();
                    }

                    mDialog.dismiss();
                }
            });

    }
}