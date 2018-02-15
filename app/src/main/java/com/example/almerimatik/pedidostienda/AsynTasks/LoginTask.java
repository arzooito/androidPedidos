package com.example.almerimatik.pedidostienda.AsynTasks;

import android.os.AsyncTask;
import android.view.View;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.MainActivity;
import com.example.almerimatik.pedidostienda.ws.Ws;

/**
 * Created by arzoo on 10/02/2018.
 */

public class LoginTask extends AsyncTask<String, Void, Void> {

    boolean autenticado = false;
    MainActivity main;

    public LoginTask(MainActivity main){
        this.main = main;
    }

    protected void onPreExecute(){

        main.getTvProgress().setText(R.string.conectando_msg);
        main.getProgressBar().setVisibility(View.VISIBLE);
    }

    protected Void doInBackground(String... params) {

        login(params[0],params[1]);
        return null;
    }

    protected void onPostExecute(Void result) {

        main.getProgressBar().setVisibility(View.GONE);

        if(autenticado){
            if(main.isRemember()){
                main.setUsuarioPref();
            }

            main.setUsuarioSesion();
            main.getLoginFragment().dismiss();
            main.iniciar();
        }else{
            main.getLoginFragment().clearInputs();
            main.mensajeErrorLogueo();
        }
    }

    private void login(String usuario, String password){
        main.setIdUsuario(Ws.login(usuario,password));
        autenticado = !(main.getIdUsuario() < 0);
    }

}
