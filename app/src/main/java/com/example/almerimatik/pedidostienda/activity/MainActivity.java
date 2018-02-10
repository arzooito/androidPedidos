package com.example.almerimatik.pedidostienda.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.Dialogs.LoginDialog;
import com.example.almerimatik.pedidostienda.Dialogs.RegistroDialog;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.ws.Ws;

public class MainActivity  extends FragmentActivity {

    long idUsuario;
    String nameUser = null;
    String pass = null;
    boolean remember = false;
    LoginDialog loginFragment;
    DialogFragment registroFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sesion.init(this);
        iniciar();
    }

    public void abrirMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }


    public void abrirLogin() {
        if(registroFragment != null && registroFragment.isResumed()){
            registroFragment.dismiss();
        }
        loginFragment = new LoginDialog();
        loginFragment.show(getFragmentManager(),"LoginDialog");
    }

    public void abrirRegistrar() {
        if(loginFragment.isResumed()){
            loginFragment.dismiss();
        }
        registroFragment = new RegistroDialog();
        registroFragment.show(getFragmentManager(),"RegistroDialog");
    }


    public void iniciar(){
        idUsuario = Sesion.getIdUsuario();

        if(idUsuario < 0){
            abrirLogin();
        }else{
            abrirMenuPrincipal();
        }

    }

    public void setUsuarioPref(){
        Data.setIdUsuario(this,idUsuario);
        Data.setNombreUsuario(this,nameUser);
        Data.setPassword(this,pass);
    }

    public void setUsuarioSesion(){
        Sesion.setIdUsuario(idUsuario);
        Sesion.setNombreUsuario(nameUser);
        Sesion.setPassword(pass);
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public void mensajeErrorLogueo(){
        Toast.makeText(this,R.string.error_login,Toast.LENGTH_LONG).show();
    }

    public void mensajeErrorRegistro(){
        Toast.makeText(this,R.string.error_registro,Toast.LENGTH_LONG).show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

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
                if(remember){
                    setUsuarioPref();
                }
                
                setUsuarioSesion();
                loginFragment.dismiss();
                iniciar();
            }else{
                loginFragment.clearInputs();
                mensajeErrorLogueo();
            }
        }

        private void login(String usuario, String password){
            idUsuario = Ws.login(usuario,password);
            autenticado = !(idUsuario < 0);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class RegistrarTask extends AsyncTask<String, Void, Void> {

        boolean registrado = false;

        protected Void doInBackground(String... params) {
            registrar(params[0],params[1],params[2],params[3]);
            return null;
        }

        protected void onPostExecute(Void result) {
            if(registrado){
                setUsuarioSesion();
                registroFragment.dismiss();
                iniciar();
            }else{
                mensajeErrorRegistro();
            }
        }

        private void registrar(String usuario, String email, String telefono, String password){
            long idUsuario = Ws.registrarUsuario(usuario, email, telefono, password);
            registrado = !(idUsuario < 0);
        }
    }

}
