package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.activity.CatalogoActivity;
import com.example.almerimatik.pedidostienda.dialogs.CargandoDialog;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 01/05/2018.
 */

public class CargarCatalogoTask extends AsyncTask<Void, Void, ArrayList<Producto>> {

    Activity act;
    CargandoDialog dialog;

    public CargarCatalogoTask(Activity act){
        this.act = act;
    }

    protected void onPreExecute(){

        dialog = new CargandoDialog();
        dialog.show(act.getFragmentManager(),"CargandoDialog");
    }

    protected ArrayList<Producto> doInBackground(Void... params) {

        BD bd = new BD(act);
        bd.openBD(false);
        ArrayList<Producto> lista = bd.cargarProductos();
        bd.closeBD();

        return lista;
    }

    protected void onPostExecute(ArrayList<Producto> result) {

        dialog.dismiss();
        Intent intent = new Intent(act, CatalogoActivity.class);
        intent.putExtra("lista", (Serializable) result);
        act.startActivity(intent);
    }
}
