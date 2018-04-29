package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.util.ArrayList;


/**
 * Created by Almerimatik on 18/01/2018.
 */

public class ListadoBaseActivity <E, A extends ArrayAdapter<E>>extends BaseActivity{

    private ListView lvLista;
    TextView emptyLabel;

    protected void onCreate(Bundle savedInstanceState,final A adapter) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this,R.layout.content_listado_base);
        emptyLabel = findViewById(R.id.emptyLabel);
        lvLista = (ListView) findViewById(R.id.lista);
        lvLista.setAdapter(adapter);
        final ArrayList<E> lista = (ArrayList<E>) getIntent().getParcelableArrayListExtra("lista");
        rellenarLista(lista);
    }


    protected void rellenarLista(final ArrayList<E> lista) {
        if (lista != null && !lista.isEmpty()) {
            emptyLabel.setVisibility(View.GONE);
            try {
                final A adapter = (A) lvLista.getAdapter();
                adapter.clear();
                for (final E t : lista) {
                    adapter.add(t);
                }
            } catch (final Exception ex) {
                Log.e("ListadoBase", "Error al rellenar lista", ex);
            }
        }else{
            emptyLabel.setVisibility(View.VISIBLE);
        }
    }

}
