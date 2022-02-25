package com.example.myclassschedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adaptereliminarcurso extends RecyclerView.Adapter<adaptereliminarcurso.eliminarCursosviewHolder> implements View.OnClickListener {



    List<curso> cursos ;
    private View.OnClickListener listener;



    public adaptereliminarcurso(List<curso> cursos) {
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public adaptereliminarcurso.eliminarCursosviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listrecyclervieweliminarcurso, parent, false);
        adaptereliminarcurso.eliminarCursosviewHolder holder= new adaptereliminarcurso.eliminarCursosviewHolder(v);

        v.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull adaptereliminarcurso.eliminarCursosviewHolder holder, int position) {

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

    public void setOnClickListener(View.OnClickListener listener){

        this.listener=listener;

    }

    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class eliminarCursosviewHolder extends RecyclerView.ViewHolder {


        TextView nombre,grupo,dia,hora,aula,docente;

        public eliminarCursosviewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.txtnombrerecycleEliminar);
            grupo=itemView.findViewById(R.id.txtgruporecycleEliminar);
            dia=itemView.findViewById(R.id.txtdiarecycleEliminar);
            hora=itemView.findViewById(R.id.txthorarecycleEliminar);
            aula= itemView.findViewById(R.id.txtaularecycleEliminar);
            docente=itemView.findViewById(R.id.txtdocenterecycleEliminar);

        }
    }
}
