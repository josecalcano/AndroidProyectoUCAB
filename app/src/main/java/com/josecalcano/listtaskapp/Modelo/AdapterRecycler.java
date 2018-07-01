package com.josecalcano.listtaskapp.Modelo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.josecalcano.listtaskapp.ListaTareas;
import com.josecalcano.listtaskapp.R;

import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    RealmResults<Tareas> tareas;
    ListaTareas listaTareas;
    Realm realm;
    public AdapterRecycler(RealmResults<Tareas> tareas,ListaTareas listaTareas,Realm realm){
        this.tareas = tareas;
        this.listaTareas = listaTareas;
        this.realm = realm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_alltasks,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.VistaActual(position);
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvDate;
        CheckBox checkBox;
        SimpleDateFormat d;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            d = new SimpleDateFormat("dd-MM-yy");
            tvNombre = itemView.findViewById(R.id.TVnameTask);
            tvDate = itemView.findViewById(R.id.TVDateTask);
            checkBox = itemView.findViewById(R.id.checkBox);
            ;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked() == true){
                        listaTareas.ActualizarLista();
                        realm.beginTransaction();
                        tareas.get(position).setCompleta(checkBox.isChecked());
                        realm.commitTransaction();
                        //tareas.get(position).setCompleta(true);
                    }
                }
            });
        }

        public void VistaActual(int position){
            this.position =position;
            checkBox.setChecked(tareas.get(position).getCompleta());
            if (tareas.get(position).getCompleta()){
                checkBox.setEnabled(false);
            }
            //tvDate.setText(d.parse(tareas.get(position).getFechacreacion()).toString());
            tvDate.setText(d.format(tareas.get(position).getFechacreacion()));


            tvNombre.setText(tareas.get(position).getNombre());
        }
    }
}

