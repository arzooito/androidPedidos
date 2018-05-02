package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.RecogerActivity;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.dialogs.EnviandoDialog;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Msg;
import com.example.almerimatik.pedidostienda.tools.XML;
import com.example.almerimatik.pedidostienda.ws.Ws;

import java.io.Serializable;

/**
 * Created by arzoo on 30/04/2018.
 */

public class TramitarPedidoTask extends AsyncTask<Pedido, Void, Pedido> {

    Activity act;
    long superID = 0;
    EnviandoDialog dialog;

    public TramitarPedidoTask(Activity act){
        this.act = act;
    }

    protected void onPreExecute(){

        dialog = new EnviandoDialog();
        dialog.show(act.getFragmentManager(),"EnviandoDialog");
    }

    protected Pedido doInBackground(Pedido... params) {

        enviarPedido(params[0]);
        guardarPedido(params[0]);
        return params[0];
    }

    protected void onPostExecute(Pedido result) {

        dialog.dismiss();

        if(superID > 0){
            Sesion.getCarrito().clear();
            Intent intent = new Intent(act, RecogerActivity.class);
            intent.putExtra("pedido", (Serializable) result);
            act.startActivity(intent);
        }else{
            Msg.mensaje(act, R.string.error_envio,R.string.error_enviar, false);
        }
    }

    private void guardarPedido(Pedido ped){
        BD bd = new BD(act);
        if(ped != null) {
            bd.openBD(true);
            bd.guardarPedido(ped);
            bd.closeBD();
        }
    }

    private void enviarPedido(Pedido ped){

        String pedidoXML = XML.crearPedido(ped);
        System.out.println(pedidoXML);
        superID = Ws.guardarPedido(pedidoXML);
        ped.setSuperID(superID);

    }
}
