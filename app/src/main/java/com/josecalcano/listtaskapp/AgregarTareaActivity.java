package com.josecalcano.listtaskapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.josecalcano.listtaskapp.Modelo.DAOTareas;
import com.josecalcano.listtaskapp.Modelo.Tareas;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AgregarTareaActivity extends AppCompatActivity {

    TextView tvnombre,tvdate;
    EditText editTextnombre;
    CheckBox checkBox;
    Button button;
    String fecha ;
    Date date;
    RealmConfiguration config;
    Realm realm;
    DAOTareas dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(config);
        setContentView(R.layout.activity_agregar_tarea);
        tvnombre = findViewById(R.id.tvnombretarea);
        tvdate = findViewById(R.id.etfecha);
        editTextnombre = findViewById(R.id.etnombre);
        checkBox = findViewById(R.id.checkbox2);
        button = findViewById(R.id.button);
        dao = new DAOTareas(realm);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = new Date();

        fecha = dateFormat.format(date);
        tvdate.setText(fecha);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Tareas tareas = new Tareas();
                tareas.setNombre(editTextnombre.getText().toString());
                tareas.setCompleta(checkBox.isChecked());
                tareas.setFechacreacion(date);
                if (checkBox.isChecked()){

                    tareas.setFechafinalizacion(date);
                }
                dao.InsertarTarea(tareas);
                finish();
                setResult(1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        realm.close();
    }
}