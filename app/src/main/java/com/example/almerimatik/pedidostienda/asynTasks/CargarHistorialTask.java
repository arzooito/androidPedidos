package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.HistorialActivity;
import com.example.almerimatik.pedidostienda.activity.RecogerActivity;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.dialogs.CargandoDialog;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Msg;
import com.example.almerimatik.pedidostienda.tools.XML;
import com.example.almerimatik.pedidostienda.ws.Ws;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 01/05/2018.
 */

public class CargarHistorialTask extends AsyncTask<Void, Void, ArrayList<Pedido>>{

    Activity act;
    CargandoDialog dialog;

    public CargarHistorialTask(Activity act){
        this.act = act;
    }

    protected void onPreExecute(){

        dialog = new CargandoDialog();
        dialog.show(act.getFragmentManager(),"CargandoDialog");
    }

    protected ArrayList<Pedido> doInBackground(Void... params) {

        BD bd = new BD(act);
        bd.openBD(false);
        ArrayList<Pedido> lista = bd.cargarPedidos();
        bd.closeBD();

        return lista;
    }

    protected void onPostExecute(ArrayList<Pedido> result) {

        dialog.dismiss();
        Intent intent = new Intent(act, HistorialActivity.class);
        intent.putExtra("lista", (Serializable) result);
        act.startActivity(intent);
    }

}
