package com.example.myclassschedule;

public class curso {

    String email;
    String nombre;
    String grupo;
    String dia;
    String hora;
    String aula;
    String docente;
    String id;

    public curso() {
    }

    public curso(String email, String nombre, String grupo, String dia, String hora, String aula, String docente,String id) {
        this.email = email;
        this.nombre = nombre;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
        this.aula = aula;
        this.docente = docente;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
