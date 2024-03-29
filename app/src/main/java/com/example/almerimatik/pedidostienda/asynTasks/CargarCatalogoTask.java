package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.activity.CatalogoAListaActivity;
import com.example.almerimatik.pedidostienda.activity.CatalogoActivity;
import com.example.almerimatik.pedidostienda.dialogs.CargandoDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
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
    Lista lis;

    public CargarCatalogoTask(Activity act){
        this.act = act;
    }

    public CargarCatalogoTask(Activity act, Lista lis){
        this.act = act;
        this.lis = lis;
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
        Intent intent;
        if(lis == null){
            intent = new Intent(act, CatalogoActivity.class);
        }else{
            intent = new Intent(act, CatalogoAListaActivity.class);
            intent.putExtra("objetoLista", lis);
        }
        intent.putExtra("lista", (Serializable) result);
        act.startActivity(intent);

    }
}
