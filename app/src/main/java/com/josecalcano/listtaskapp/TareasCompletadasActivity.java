package com.josecalcano.listtaskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import com.josecalcano.listtaskapp.Modelo.AdapterRecycler;
import com.josecalcano.listtaskapp.Modelo.DAOTareas;

public class TareasCompletadasActivity extends AppCompatActivity implements ListaTareas {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AdapterRecycler adapterRecycler;
    RealmConfiguration config;
    Realm realm;
    DAOTareas daoTareas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas_completadas);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("Proyecto2");
        config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(config);
        daoTareas = new DAOTareas(realm);

        recyclerView = findViewById(R.id.RecyclerCompleteTasks);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        adapterRecycler = new AdapterRecycler(daoTareas.TareasCompletadas(),this,realm);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecycler);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void ActualizarLista() {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

