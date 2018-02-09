package com.example.almerimatik.pedidostienda.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.example.almerimatik.pedidostienda.Dialogs.LoginDialog;
import com.example.almerimatik.pedidostienda.Dialogs.RegistroDialog;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.ws.Ws;

public class MainActivity  extends FragmentActivity {

    long idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar();
    }

    public void abrirMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }


    public void abrirLogin() {
        DialogFragment newFragment = new LoginDialog();
        newFragment.show(getFragmentManager(),"LoginDialog");
    }

    public void abrirRegistrar() {
        DialogFragment newFragment = new RegistroDialog();
        newFragment.show(getFragmentManager(),"RegistroDialog");
    }


    public void iniciar(){

        idUsuario = Data.getIdUsuario(this);
        if(idUsuario < 0){
            abrirLogin();
        }else{
            abrirMenuPrincipal();
        }

    }

    public void setUsuario(){
        Data.setIdUsuario(this,idUsuario);
    }

    public class LoginTask extends AsyncTask<String, Void, Void> {

        boolean autenticado = false;

        //protected void onPreExecute(){
        //progress.show();
        // }

        protected Void doInBackground(String... params) {
            login(params[0],params[1]);
            return null;
        }

        protected void onPostExecute(Void result) {
            if(autenticado){
                setUsuario();
                iniciar();
            }
        }

        private void login(String usuario, String password){
            idUsuario = Ws.login(usuario,password);
            autenticado = !(idUsuario < 0);
        }

    }

    public class RegistrarTask extends AsyncTask<String, Void, Void> {

        boolean registrado = false;

        protected Void doInBackground(String... params) {
            registrar(params[0],params[1],params[2],params[3]);
            return null;
        }

        protected void onPostExecute(Void result) {
            if(registrado){

            }
        }

        private void registrar(String usuario, String email, String telefono, String password){
            long idUsuario = Ws.registrarUsuario(usuario, email, telefono, password);
            registrado = !(idUsuario < 0);
        }
    }


}
