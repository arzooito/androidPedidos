package com.example.almerimatik.pedidostienda.activity;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.asynTasks.ActualizarTask;
import com.example.almerimatik.pedidostienda.dialogs.LoginDialog;
import com.example.almerimatik.pedidostienda.dialogs.RegistroDialog;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.constantes.Sesion;

public class MainActivity  extends FragmentActivity {

    long idUsuario;
    String nameUser = null;
    String pass = null;
    boolean remember = false;
    LoginDialog loginFragment;
    DialogFragment registroFragment;
    LinearLayout progressBarBox;
    ProgressBar progressBar;
    TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarBox = (LinearLayout) findViewById(R.id.progressBoxMain);
        progressBar = (ProgressBar) findViewById(R.id.progressBarMain);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


        Sesion.init(this);
        actualizar();

    }

    @Override
    public void onBackPressed() {
        iniciar();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public LinearLayout getProgressBar() {
        return progressBarBox;
    }

    public TextView getTvProgress() { return  tvProgress; }

    public LoginDialog getLoginFragment() {
        return loginFragment;
    }

    public DialogFragment getRegistroFragment() {
        return registroFragment;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public boolean isRemember() {
        return remember;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void iniciar(){
        idUsuario = Sesion.getIdUsuario();

        if(idUsuario < 0){
            abrirLogin();
        }else{
            abrirMenuPrincipal();
        }

    }

    public void actualizar(){

        new ActualizarTask(this).execute();
    }

    public void abrirMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    public void mensajeErrorLogueo(){
        Toast.makeText(this,R.string.error_login,Toast.LENGTH_LONG).show();
    }

    public void mensajeErrorRegistro(){
        Toast.makeText(this,R.string.error_registro,Toast.LENGTH_LONG).show();
    }

}
