package com.example.myclassschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterinicioActividad extends RecyclerView.Adapter<adapterinicioActividad.actualizarActividadviewHolder> {


    List<datos_actividad> actividad ;

    public adapterinicioActividad(List<datos_actividad> actividad) {
        this.actividad = actividad;
    }

    @NonNull
    @Override
    public actualizarActividadviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecyclerviewinicioactividad, parent, false);
        adapterinicioActividad.actualizarActividadviewHolder holder= new  adapterinicioActividad.actualizarActividadviewHolder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull actualizarActividadviewHolder holder, int position) {
        datos_actividad curso= actividad.get(position);

        holder.nombreCurso.setText(curso.getCurso());
        holder.descripcion.setText(curso.getDescripcion());
        holder.fecha.setText(curso.getFecha());
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
