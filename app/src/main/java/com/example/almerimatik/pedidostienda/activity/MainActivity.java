package com.example.almerimatik.pedidostienda.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.example.almerimatik.pedidostienda.Dialogs.LoginDialog;
import com.example.almerimatik.pedidostienda.Dialogs.RegistroDialog;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.ws.Ws;

public class MainActivity  extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    public void abrirMenuPrincipal(View view) {
        abrirMenuPrincipal();
    }

    public void abrirLogin() {
        DialogFragment newFragment = new LoginDialog();
        newFragment.show(getFragmentManager(),"LoginDialog");
    }


    public void abrirLogin(View view) {
        abrirLogin();

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
                abrirMenuPrincipal();
            }
        }

        private void login(String usuario, String password){
            autenticado = Ws.login(usuario,password);
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
                abrirMenuPrincipal();
            }
        }

        private void registrar(String usuario, String email, String telefono, String password){
            registrado = Ws.registrarUsuario(usuario, email, telefono, password);
        }
    }


}
