package com.example.myclassschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterActualizarActividad extends RecyclerView.Adapter<adapterActualizarActividad.actualizarActividadviewHolder> implements View.OnClickListener {



    private View.OnClickListener listener;
    List<datos_actividad> actividad ;

    public adapterActualizarActividad(List<datos_actividad> actividad) {
        this.actividad = actividad;
    }



    @NonNull
    @Override
    public actualizarActividadviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecyclerviewactualizaractividad, parent, false);
        adapterActualizarActividad.actualizarActividadviewHolder holder= new adapterActualizarActividad.actualizarActividadviewHolder(v);

        v.setOnClickListener(this);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull actualizarActividadviewHolder holder, int position) {
        datos_actividad curso= actividad.get(position);

        holder.nombreCurso.setText(curso.getCurso());
        holder.descripcion.setText(curso.getDescripcion());
        holder.fecha.setText(curso.getFecha());
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener=listener;

    }

    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }
    }

    @Override
        public int getItemCount() {
            return actividad.size();
        }


        public class actualizarActividadviewHolder extends RecyclerView.ViewHolder {


            TextView nombreCurso,descripcion,fecha;

            public actualizarActividadviewHolder(@NonNull View itemView) {
                super(itemView);

                nombreCurso=itemView.findViewById(R.id.txtCursoinicioActividad);
                descripcion=itemView.findViewById(R.id.txtdescripcioninicioActividad);
                fecha=itemView.findViewById(R.id.txtfechainicioActividad);

            }
        }


    }

