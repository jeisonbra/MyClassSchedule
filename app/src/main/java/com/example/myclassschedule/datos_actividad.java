package com.example.myclassschedule;

public class datos_actividad {

    String curso,descripcion,fecha, email, id;


    public datos_actividad() {
    }
    public datos_actividad(String id,String curso, String descripcion, String fecha, String email) {
        this.id=id;
        this.curso = curso;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.email=email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
