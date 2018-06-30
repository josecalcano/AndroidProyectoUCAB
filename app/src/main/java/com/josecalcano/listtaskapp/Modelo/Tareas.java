package com.josecalcano.listtaskapp.Modelo;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;

public class Tareas extends RealmObject{
    private Date fechacreacion;
    private Date fechafinalizacion;
    private String nombre;
    private Boolean completa;

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public Date getFechafinalizacion() {
        return fechafinalizacion;
    }

    public void setFechafinalizacion(Date fechafinalizacion) {
        this.fechafinalizacion = fechafinalizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void setCompleta(Boolean completa) {
        this.completa = completa;
    }
}
