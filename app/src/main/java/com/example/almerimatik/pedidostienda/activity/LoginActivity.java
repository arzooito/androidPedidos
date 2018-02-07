package com.example.almerimatik.pedidostienda.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.almerimatik.pedidostienda.R;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario,  etPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etUsuario = (EditText) findViewById (R.id.et_login_usuario);
        etPassword = (EditText) findViewById (R.id.et_login_password);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
    }

    public void enviar(View view){

    }
    

}


