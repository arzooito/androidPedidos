package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;

import com.example.almerimatik.pedidostienda.adaptadores.CatalogoAdapter;
import com.example.almerimatik.pedidostienda.entity.Producto;

import static com.example.almerimatik.pedidostienda.constantes.Tipo.LISTA;

/**
 * Created by Almerimatik on 06/02/2018.
 */

public class ListaActivity extends ListadoBaseActivity<Producto,CatalogoAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
