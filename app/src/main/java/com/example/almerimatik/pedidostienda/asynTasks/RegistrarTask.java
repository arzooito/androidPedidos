package com.example.almerimatik.pedidostienda.asynTasks;

import android.os.AsyncTask;
import android.view.View;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.MainActivity;
import com.example.almerimatik.pedidostienda.ws.Ws;

/**
 * Created by arzoo on 10/02/2018.
 */

public class RegistrarTask extends AsyncTask<String, Void, Void> {

    boolean registrado = false;
    MainActivity main;

    public RegistrarTask(MainActivity main){
        this.main = main;
    }

    protected void onPreExecute(){
        main.getTvProgress().setText(R.string.conectando_msg);
        main.getProgressBar().setVisibility(View.VISIBLE);
    }

    protected Void doInBackground(String... params) {
        registrar(params[0],params[1],params[2],params[3]);
        return null;
    }

    protected void onPostExecute(Void result) {

        main.getProgressBar().setVisibility(View.GONE);

        if(registrado){
            main.setUsuarioSesion();
            main.getRegistroFragment().dismiss();
            main.iniciar();
        }else{
            main.mensajeErrorRegistro();
        }
    }

    private void registrar(String usuario, String email, String telefono, String password){
        long idUsuario = Ws.registrarUsuario(usuario, email, telefono, password);
        registrado = !(idUsuario < 0);
    }
}
