package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.Tools.Contenido;
import com.example.almerimatik.pedidostienda.Tools.Msg;
import static com.example.almerimatik.pedidostienda.constantes.Tipo.PRINCIPAL;

public class MenuPrincipalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this,R.layout.content_menu_principal);
        activityTipo = PRINCIPAL;
    }

    @Override
    public void onBackPressed() {

        Msg.salir(this);
    }

    public void abrirCatalogo(View view) {

        Intent intent = new Intent(this, CatalogoActivity.class);
        startActivity(intent);
    }

    public void abrirListas(View view) {

        Intent intent = new Intent(this, ListasActivity.class);
        startActivity(intent);
    }

    public void abrirHistorial(View view) {

        Intent intent = new Intent(this, HistorialActivity.class);
        startActivity(intent);
    }
}
