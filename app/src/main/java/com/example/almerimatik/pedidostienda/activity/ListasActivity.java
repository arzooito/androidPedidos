package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;

import com.example.almerimatik.pedidostienda.adaptadores.ListaAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.PedidoAdapter;
import com.example.almerimatik.pedidostienda.entity.Lista;


/**
 * Created by Almerimatik on 06/02/2018.
 */

public class ListasActivity extends ListadoBaseActivity<Lista, ListaAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new ListaAdapter(this));
    }
}
