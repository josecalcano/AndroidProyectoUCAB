package com.josecalcano.listtaskapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.josecalcano.listtaskapp.Modelo.AdapterRecycler;
import com.josecalcano.listtaskapp.Modelo.DAOTareas;
import com.josecalcano.listtaskapp.Modelo.Tareas;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements ListaTareas{

    RecyclerView recyclerView;
    AdapterRecycler adapterRecycler;
    RealmConfiguration config;
    Realm realm;
    RealmResults<Tareas> listatareas;
    DAOTareas daoTareas;
    LinearLayoutManager linearLayoutManager ;
    FloatingActionButton actionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("ListTaskApp");
        myToolbar.inflateMenu(R.menu.menu);

        config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(config);

        daoTareas = new DAOTareas(realm);
        listatareas = daoTareas.ConsultarTareas();
        adapterRecycler= new AdapterRecycler(listatareas,this,realm);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.RecyclerAllTasks);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecycler);
        actionButton = findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AgregarTareaActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public void ActualizarLista() {
        listatareas = daoTareas.ConsultarTareas();
        adapterRecycler.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1){
            adapterRecycler.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(MainActivity.this,TareasCompletadasActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}

