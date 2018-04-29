package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;

import com.example.almerimatik.pedidostienda.adaptadores.CatalogoAdapter;
import com.example.almerimatik.pedidostienda.entity.Producto;


public class CatalogoActivity extends ListadoBaseActivity<Producto,CatalogoAdapter>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new CatalogoAdapter(this));
    }

}
