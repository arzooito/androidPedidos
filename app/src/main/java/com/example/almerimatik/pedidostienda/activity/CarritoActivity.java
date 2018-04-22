package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.util.ArrayList;


/**
 * Created by Almerimatik on 06/02/2018.
 */

public class CarritoActivity extends BaseActivity{

    private ListView lvLista;
    CarritoAdapter adapter;
    LinearLayout emptyLabel;
    LinearLayout footer;
    boolean listaEmpty;
    Button irCatalogo, realizarPedido;
    TextView tvImporteTotal;
    float importeTotal = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CarritoAdapter(this);
        Contenido.addContent(this, R.layout.content_listado_carrito);
        emptyLabel = findViewById(R.id.emptyLabel);
        footer = findViewById(R.id.footer);
        lvLista = (ListView) findViewById(R.id.lista);
        irCatalogo = (Button) findViewById(R.id.btn_ir_a_catalogo);
        realizarPedido = (Button) findViewById(R.id.btn_realizar_pedido);
        tvImporteTotal = (TextView) findViewById(R.id.importeTotal);

        irCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCatalogo();
            }
        });

        realizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarPedido();
            }
        });

        lvLista.setAdapter(adapter);
        final ArrayList<Producto> lista = (ArrayList<Producto>) getIntent().getSerializableExtra("lista");
        listaEmpty = lista.isEmpty();
        rellenarLista(lista);


    }


    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            listaVacia(false);
            try {
                final CarritoAdapter adapter = (CarritoAdapter) lvLista.getAdapter();
                adapter.clear();
                for (final Producto reg : lista) {
                    adapter.add(reg);
                    importeTotal += reg.getCantidad() * reg.getPrecio();
                }

                tvImporteTotal.setText(String.format("%.2f €",importeTotal));
            } catch (final Exception ex) {
                Log.e("ListadoBase", "Error al rellenar lista", ex);
            }
        }else{
            listaVacia(true);
        }
    }

    private void listaVacia(boolean vacia){

        if(vacia){
            emptyLabel.setVisibility(View.VISIBLE);
            lvLista.setVisibility(View.GONE);
            footer.setVisibility(View.GONE);
            deshabilitarMenu();

        }else{
            emptyLabel.setVisibility(View.GONE);
            lvLista.setVisibility(View.VISIBLE);
            footer.setVisibility(View.VISIBLE);
        }
    }

    private void limpiarCarrito(){

        Sesion.getCarrito().clear();
        adapter.clear();
        listaVacia(true);
    }

    private void deshabilitarMenu(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
    }

    private void guardarCarrito(){

    }

    private void realizarPedido(){

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        if(!listaEmpty){
            final MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_carrito, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.limpiar_carrito:
                limpiarCarrito();
                return true;

            case R.id.guardar_en_lista:
                guardarCarrito();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
