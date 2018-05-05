package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.ListaAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.ResumenAdapter;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 05/05/2018.
 */

public class PedidoActivity extends BaseActivity{

    private ListView lvLista;
    private ResumenAdapter adapter;
    private LinearLayout footer;
    private Button btnConfirmarPedido;
    private ArrayList<Producto> lista;
    private Pedido ped;
    private float importeTotal = 0;
    private TextView tvImporteTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this, R.layout.activity_realizar_pedido);

        footer = findViewById(R.id.fechaHora);
        lvLista = (ListView) findViewById(R.id.lvLista);
        btnConfirmarPedido = (Button) findViewById(R.id.btn_confirmar_pedido);
        tvImporteTotal = (TextView) findViewById(R.id.tvTotal);

        btnConfirmarPedido.setVisibility(View.GONE);
        footer.setVisibility(View.GONE);

        ped = (Pedido) getIntent().getSerializableExtra("objetoPedido");
        lista = ped.getProductos();
        String titulo = String.format("Pedido id. %05d", ped.getSuperID());
        setTitle(titulo);
        rellenarLista(lista);

    }


    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            try {
                adapter = new ResumenAdapter(this);
                for (final Producto reg : lista) {
                    adapter.add(reg);
                    importeTotal += reg.getCantidad() * reg.getPrecio();
                }
                lvLista.setAdapter(adapter);
                tvImporteTotal.setText(String.format("%.2f €",importeTotal));
            } catch (final Exception ex) {
                Log.e("ListadoCarrito", "Error al rellenar lista", ex);
            }
        }
    }

}
