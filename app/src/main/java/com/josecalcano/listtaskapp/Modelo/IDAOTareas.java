package com.josecalcano.listtaskapp.Modelo;

import io.realm.RealmResults;

public interface IDAOTareas {

    public Tareas ConsultarTarea(int id);

    public RealmResults<Tareas> ConsultarTareas();

    public void InsertarTarea(Tareas tarea);

    public void EliminarTarea(int id);


}