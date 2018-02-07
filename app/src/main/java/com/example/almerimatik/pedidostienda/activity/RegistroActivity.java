package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.almerimatik.pedidostienda.R;

public class RegistroActivity extends LoginActivity {

    EditText etEmail,  etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById (R.id.et_login_email);
        etTelefono = (EditText) findViewById (R.id.et_login_telefono);
    }

    @Override
    public void enviar(View view) {

    }
}
