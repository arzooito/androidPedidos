package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.activity.ListasActivity;
import com.example.almerimatik.pedidostienda.dialogs.CargandoDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 05/05/2018.
 */

public class BorrarListaTask extends AsyncTask<Lista, Void, Void> {

    Activity act;

    public BorrarListaTask(Activity act){
        this.act = act;
    }

    protected Void doInBackground(Lista... params) {

        BD bd = new BD(act);
        bd.openBD(true);
        bd.eliminarLista(params[0]);
        bd.closeBD();

        return null;
    }

}
