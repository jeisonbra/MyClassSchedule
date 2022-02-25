package com.example.myclassschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterEliminaractividad extends RecyclerView.Adapter<adapterEliminaractividad.eliminarActividadViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    List<datos_actividad> actividad ;


    public adapterEliminaractividad(List<datos_actividad> actividad) {
        this.actividad = actividad;
    }

    @NonNull
    @Override
    public adapterEliminaractividad.eliminarActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecyclervieweliminaractividad, parent, false);
        adapterEliminaractividad.eliminarActividadViewHolder holder= new adapterEliminaractividad.eliminarActividadViewHolder(v);


        v.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adapterEliminaractividad.eliminarActividadViewHolder holder, int position) {
        datos_actividad curso= actividad.get(position);

        holder.nombre.setText(curso.getCurso());
        holder.descripcion.setText(curso.getDescripcion());
        holder.fecha.setText(curso.getFecha());
    }

    @Override
    public int getItemCount() {
        return actividad.size();
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
    public class eliminarActividadViewHolder extends RecyclerView.ViewHolder {


        TextView nombre,descripcion,fecha;

        public eliminarActividadViewHolder(@NonNull View itemView) {
            super(itemView);


            nombre=itemView.findViewById(R.id.txtCursoeliminarActividad);
            descripcion=itemView.findViewById(R.id.txtdescripcioneliminarActividad);
            fecha=itemView.findViewById(R.id.txtfechaeliminarActividad);



        }
    }
}
