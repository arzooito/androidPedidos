package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.almerimatik.pedidostienda.constantes.Tipo.PRINCIPAL;

public class MenuPrincipalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this,R.layout.content_menu_principal);

        Button btnCatalogo = (Button) findViewById(R.id.btnCatalogo);
        Button btnListas = (Button) findViewById(R.id.btnListas);
        Button btnHistorial = (Button) findViewById(R.id.btnHistorial);

        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCatalogo();
            }
        });

        btnListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListas();
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirHistorial();
            }
        });
    }

}
