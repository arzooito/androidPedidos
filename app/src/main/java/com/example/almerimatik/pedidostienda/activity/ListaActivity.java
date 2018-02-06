package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;

import static com.example.almerimatik.pedidostienda.constantes.Tipo.LISTA;

/**
 * Created by Almerimatik on 06/02/2018.
 */

public class ListaActivity extends ListadoProductos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTipo = LISTA;
    }
}
