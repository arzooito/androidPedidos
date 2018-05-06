package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CatalogoListaAdapter;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 06/05/2018.
 */

public class CatalogoAListaActivity  extends ListadoBaseActivity<Producto,CatalogoListaAdapter> {

    Lista lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new CatalogoListaAdapter(this));
        String titulo = getString(R.string.add_to) + " " + lis.getNombre();
        setTitle(titulo);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        return true;
    }

    @Override
    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            emptyLabel.setVisibility(View.GONE);
            try {
                lis = (Lista) getIntent().getSerializableExtra("objetoLista");
                final CatalogoListaAdapter adapter = new CatalogoListaAdapter(this, lis);
                for (final Producto prod : lista) {
                    adapter.add(prod);
                }
                lvLista.setAdapter(adapter);
            } catch (final Exception ex) {
                Log.e("ListadoBase", "Error al rellenar lista", ex);
            }
        }else{
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            abrirLista(lis);
        }
    }

    public void abrirLista(Lista lis){
        Intent intent = new Intent(this,  ListaActivity.class);
        intent.putExtra("objetoLista", (Serializable) lis);
        startActivity(intent);
    }

    protected void deshabilitarMenu(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
    }
}
