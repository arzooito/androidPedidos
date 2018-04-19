package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by Almerimatik on 06/02/2018.
 */

public class CarritoActivity extends BaseActivity{

    private ListView lvLista;
    CarritoAdapter adapter;
    TextView emptyLabel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CarritoAdapter(this);
        Contenido.addContent(this, R.layout.content_listado_base);
        emptyLabel = findViewById(R.id.emptyLabel);
        lvLista = (ListView) findViewById(R.id.lista);
        lvLista.setAdapter(adapter);
        final ArrayList<Producto> lista = (ArrayList<Producto>) getIntent().getSerializableExtra("lista");
        rellenarLista(lista);
    }


    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            emptyLabel.setVisibility(View.GONE);
            try {
                final CarritoAdapter adapter = (CarritoAdapter) lvLista.getAdapter();
                adapter.clear();
                for (final Producto t : lista) {
                    adapter.add(t);
                    System.out.println(t.getNombre() + " x " + t.getCantidad());
                }
            } catch (final Exception ex) {
                Log.e("ListadoBase", "Error al rellenar lista", ex);
            }
        }else{
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }
}
