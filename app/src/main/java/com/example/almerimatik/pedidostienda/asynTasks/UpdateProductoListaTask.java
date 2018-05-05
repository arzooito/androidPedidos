package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;

/**
 * Created by arzoo on 06/05/2018.
 */

public class UpdateProductoListaTask extends AsyncTask<Object, Void, Void> {

    Activity act;

    public UpdateProductoListaTask(Activity act){
        this.act = act;
    }

    protected Void doInBackground(Object... params) {

        Lista lis = (Lista) params[0];
        Producto prod = (Producto) params[1];

        BD bd = new BD(act);
        bd.openBD(true);
        bd.updateProductoLista(lis, prod);
        bd.closeBD();

        return null;
    }
}
