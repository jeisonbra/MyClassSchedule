package com.example.myclassschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.cursosviewHolder>{

    List<curso> cursos ;


    public Adapter(List<curso> cursos) {
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public cursosviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecycleviewhorario, parent, false);
        cursosviewHolder holder= new cursosviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull cursosviewHolder holder, int position) {
               curso curso= cursos.get(position);
                holder.nombre.setText(curso.getNombre());
                holder.grupo.setText(curso.getGrupo());
                holder.dia.setText(curso.getDia());
                holder.hora.setText(curso.getHora());
                holder.aula.setText(curso.getAula());
                holder.docente.setText(curso.getDocente());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public class cursosviewHolder extends RecyclerView.ViewHolder {


        TextView nombre,grupo,dia,hora,aula,docente;

        public cursosviewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.txtnombreActividadActualizar);
            grupo=itemView.findViewById(R.id.txtgrupoActividadActualizar);
            dia=itemView.findViewById(R.id.txtdiaActividadActualizar);
            hora=itemView.findViewById(R.id.txthoraActividadActualizar);
            aula= itemView.findViewById(R.id.txtaulaActividadActualizar);
            docente=itemView.findViewById(R.id.txtdocenteActividadActualizar);



        }
    }
}
