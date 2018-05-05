package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CatalogoAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.PedidoAdapter;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;

import java.io.Serializable;


/**
 * Created by Almerimatik on 18/01/2018.
 */

public class HistorialActivity extends ListadoBaseActivity<Pedido,PedidoAdapter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, new PedidoAdapter(this));

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Pedido pedido = (Pedido) adapterView.getAdapter().getItem(position);
                abrirLista(pedido);
            }
        });
    }

    public void abrirLista(Pedido pedido){
        Intent intent = new Intent(this,  PedidoActivity.class);
        intent.putExtra("objetoPedido", (Serializable) pedido);
        startActivity(intent);
    }
}
