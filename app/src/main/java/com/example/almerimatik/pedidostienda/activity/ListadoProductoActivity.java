package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.util.ArrayList;

/**
 * Created by arzoo on 05/05/2018.
 */

public class ListadoProductoActivity extends BaseActivity {

    protected SwipeMenuListView lvLista;
    protected CarritoAdapter adapter;
    protected LinearLayout emptyLabel;
    protected LinearLayout footer;
    protected TextView tvImporteTotal;
    protected float importeTotal = 0;
    protected ArrayList<Producto> lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this, R.layout.content_listado_carrito);
        emptyLabel = findViewById(R.id.emptyLabel);
        footer = findViewById(R.id.footer);
        lvLista = (SwipeMenuListView) findViewById(R.id.lista);

        //SwipeMenu
        SwipeMenuCreator creator = menuCreator();
        lvLista.setMenuCreator(creator);
    }

    public SwipeMenuCreator menuCreator(){

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(R.drawable.fondo_btn_delete);
                // set item width
                deleteItem.setWidth(250);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        return creator;
    }




    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            listaVacia(false);
            try {
                adapter = new CarritoAdapter(this);
                adapter.clear();
                for (final Producto reg : lista) {
                    adapter.add(reg);
                    importeTotal += reg.getCantidad() * reg.getPrecio();
                }
                lvLista.setAdapter(adapter);
                tvImporteTotal.setText(String.format("%.2f €",importeTotal));
            } catch (final Exception ex) {
                Log.e("ListadoCarrito", "Error al rellenar lista", ex);
            }
        }else{
            listaVacia(true);
        }
    }

    protected void listaVacia(boolean vacia){

        if(vacia){
            emptyLabel.setVisibility(View.VISIBLE);
            lvLista.setVisibility(View.GONE);

        }else{
            emptyLabel.setVisibility(View.GONE);
            lvLista.setVisibility(View.VISIBLE);
        }
    }

    protected void deshabilitarMenu(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
    }

    public void editarProducto(int unidades){

    }

}
