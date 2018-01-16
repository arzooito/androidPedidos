package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.almerimatik.pedidostienda.R;

public class menuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void abrirCatalogo(View view) {

        Intent intent = new Intent(this, CatalogoActivity.class);
        startActivity(intent);
    }

    public void abrirListas(View view) {
    }

    public void abrirHistorial(View view) {
    }
}
