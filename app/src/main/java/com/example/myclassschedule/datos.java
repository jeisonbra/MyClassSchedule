package com.example.myclassschedule;

import java.io.Serializable;

public class datos implements Serializable {

    String email;
    String nombre;
    String grupo;
    String dia;
    String hora;
    String aula;
    String docente;

    public datos(String email, String nombre, String grupo, String dia, String hora, String aula, String docente) {
        this.email = email;
        this.nombre = nombre;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
        this.aula = aula;
        this.docente = docente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }
}
