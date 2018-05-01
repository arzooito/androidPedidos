package com.example.almerimatik.pedidostienda.asynTasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Msg;
import com.example.almerimatik.pedidostienda.tools.XML;
import com.example.almerimatik.pedidostienda.ws.Ws;

/**
 * Created by arzoo on 30/04/2018.
 */

public class TramitarPedidoTask extends AsyncTask<Pedido, Void, Void> {

    Activity act;
    boolean enviado;

    public TramitarPedidoTask(Activity act){
        this.act = act;
    }

    protected void onPreExecute(){

    }

    protected Void doInBackground(Pedido... params) {

        guardarPedido(params[0]);
        enviarPedido(params[0]);
        return null;
    }

    protected void onPostExecute(Void result) {
        if(enviado){
            Msg.toast(act,R.string.exito_enviar);
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
        enviado = Ws.guardarPedido(pedidoXML);

    }
}
