package com.josecalcano.listtaskapp.Modelo;

import io.realm.Realm;
import io.realm.RealmResults;

public class DAOTareas implements IDAOTareas {

    Realm realm;

    public DAOTareas(Realm realm){
        this.realm = realm;
    }


    @Override
    public Tareas ConsultarTarea(int id) {
        Tareas tarea =realm.where(Tareas.class).equalTo("id",id).findFirst();
        return tarea;
    }

    @Override
    public RealmResults<Tareas> ConsultarTareas() {


        return realm.where(Tareas.class).equalTo("completa",false).findAll();
    }
    public RealmResults<Tareas> TareasCompletadas(){
        return realm.where(Tareas.class).equalTo("completa",true).findAll();
    }

    @Override
    public void InsertarTarea(Tareas tarea) {

        realm.beginTransaction();
        realm.insertOrUpdate(tarea);
        realm.commitTransaction();

    }

    @Override
    public void EliminarTarea(int id) {
        realm.beginTransaction();
        realm.where(Tareas.class).equalTo("id",id).findFirst().deleteFromRealm();
        realm.commitTransaction();
    }
}