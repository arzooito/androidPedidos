package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.activity.ListasActivity;
import com.example.almerimatik.pedidostienda.dialogs.CargandoDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.modelo.BD;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 01/05/2018.
 */

public class CargarListasTask extends AsyncTask<Void, Void, ArrayList<Lista>> {

    Activity act;
    CargandoDialog dialog;

    public CargarListasTask(Activity act){
        this.act = act;
    }

    protected void onPreExecute(){

        dialog = new CargandoDialog();
        dialog.show(act.getFragmentManager(),"CargandoDialog");
    }

    protected ArrayList<Lista> doInBackground(Void... params) {

        BD bd = new BD(act);
        bd.openBD(false);
        ArrayList<Lista> lista = bd.cargarListas();
        bd.closeBD();

        return lista;
    }

    protected void onPostExecute(ArrayList<Lista> result) {

        dialog.dismiss();
        Intent intent = new Intent(act, ListasActivity.class);
        intent.putExtra("lista", (Serializable) result);
        act.startActivity(intent);
    }
}
