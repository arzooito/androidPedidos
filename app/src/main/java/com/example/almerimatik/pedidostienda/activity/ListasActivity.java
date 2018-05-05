package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.ListaAdapter;
import com.example.almerimatik.pedidostienda.adaptadores.PedidoAdapter;
import com.example.almerimatik.pedidostienda.asynTasks.BorrarListaTask;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.dialogs.ListaDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;
import com.example.almerimatik.pedidostienda.tools.Msg;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Almerimatik on 06/02/2018.
 */

public class ListasActivity extends BaseActivity {

    private SwipeMenuListView lvLista;
    private ListaAdapter adapter;
    private LinearLayout emptyLabel;
    private LinearLayout footer;
    private Button irCatalogo;
    private TextView tvVacio;
    private ArrayList<Lista> lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this, R.layout.content_listado_carrito);
        emptyLabel = findViewById(R.id.emptyLabel);
        footer = findViewById(R.id.footer);
        lvLista = (SwipeMenuListView) findViewById(R.id.lista);
        tvVacio = (TextView) findViewById(R.id.texto_vacia);
        irCatalogo = (Button) findViewById(R.id.btn_ir_a_catalogo);

        tvVacio.setText(R.string.empty_list);
        irCatalogo.setVisibility(View.GONE);
        footer.setVisibility(View.GONE);


        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Lista lis = (Lista) adapterView.getAdapter().getItem(position);
                abrirLista(lis);
            }
        });

        lista = (ArrayList<Lista>) getIntent().getSerializableExtra("lista");
        rellenarLista(lista);

        //SwipeMenu
        SwipeMenuCreator creator = menuCreator();
        lvLista.setMenuCreator(creator);
        setOnClickSwipeMenu(lvLista);
    }


    protected void rellenarLista(final ArrayList<Lista> lista) {
        if (lista != null && !lista.isEmpty()) {
            listaVacia(false);
            try {
                adapter = new ListaAdapter(this);
                adapter.clear();
                for (final Lista reg : lista) {
                    adapter.add(reg);
                }
                lvLista.setAdapter(adapter);
            } catch (final Exception ex) {
                Log.e("ListadoCarrito", "Error al rellenar lista", ex);
            }
        }else{
            listaVacia(true);
        }
    }

    private void listaVacia(boolean vacia){

        if(vacia){
            emptyLabel.setVisibility(View.VISIBLE);
            lvLista.setVisibility(View.GONE);

        }else{
            emptyLabel.setVisibility(View.GONE);
            lvLista.setVisibility(View.VISIBLE);
        }
    }

    public void abrirLista(Lista lis){
        Intent intent = new Intent(ListasActivity.this,  ListaActivity.class);
        intent.putExtra("objetoLista", (Serializable) lis);
        startActivity(intent);
    }


    public void eliminarItemLista(int position){
        Lista lis = (Lista)lvLista.getItemAtPosition(position);
        lista.remove(lis);
        new BorrarListaTask(this).execute(lis);
        rellenarLista(lista);
    }


    public SwipeMenuCreator menuCreator(){

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(R.drawable.fondo_btn_delete_fill);
                // set item width
                deleteItem.setWidth(180);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_40);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        return creator;
    }

    public void setOnClickSwipeMenu(final SwipeMenuListView listView){

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0://delete
                        eliminarItemLista(position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
}
