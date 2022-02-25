package com.example.myclassschedule;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class inicio extends AppCompatActivity {


    Button btnsalir, btnopciones,btnDate;
    CircleImageView perfil;
    RecyclerView listaHorario,listaactividades;
    List<curso> cursos;
    List<datos_actividad> actividad;
    adapterinicioActividad adapterActividad;
    Adapter adapter;

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar. getInstance () ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        btnsalir=findViewById(R.id.btnatrasregistro);
        btnopciones=findViewById(R.id.btnopcionesinicio);
        perfil=findViewById(R.id.imgperfil);
        listaactividades=findViewById(R.id.recyclerViewactividadespendientes);
        listaHorario=findViewById(R.id.recyclerViewhorario);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        listaHorario.setLayoutManager(new LinearLayoutManager(this));
        listaactividades.setLayoutManager(new LinearLayoutManager(this));

        cursos=new ArrayList<>();
        actividad=new ArrayList<>();

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        FirebaseDatabase databaseActividad= FirebaseDatabase.getInstance();
        adapter=new Adapter(cursos);
        adapterActividad=new adapterinicioActividad(actividad);

        listaHorario.setAdapter(adapter);
        listaactividades.setAdapter(adapterActividad);

        startAlarmBroadcastReceiver(this);


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

        databaseActividad.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                String id=idusuario().toString();
                actividad.removeAll(actividad);
                for (DataSnapshot dataSnapshot:
                    //Se ingresa la ruta para obtener los datos
                        snapshot.child(id+"/Actividades").getChildren()) {
                    datos_actividad curso=dataSnapshot.getValue(datos_actividad.class);
                    actividad.add(curso);
                }
                adapterActividad.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });




        if(user!=null){
            Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.usuario__2_).into(perfil);
        }else{
            getApplicationContext();
        }

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });

        btnopciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentOpciones();
            }
        });




    }




    public void salir(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Sesion cerrada!!!", Toast.LENGTH_SHORT).show();
        goglogin();

    }

    private void goglogin() {
        Intent inten=new Intent(this, MainActivity.class);
        inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(inten);
    }




    public void intentOpciones() {
        Intent intent=new Intent(this,opciones.class);
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




    public static void startAlarmBroadcastReceiver(Context context) {
        Intent _intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, _intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 9);
        calendar.set(Calendar.SECOND, 40);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }





    }