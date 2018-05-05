package com.example.almerimatik.pedidostienda.activity;

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
import com.example.almerimatik.pedidostienda.asynTasks.BorrarProductoListaTask;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;

import java.util.ArrayList;

/**
 * Created by arzoo on 03/05/2018.
 */

public class ListaActivity extends BaseActivity {

    private SwipeMenuListView lvLista;
    private CarritoAdapter adapter;
    private LinearLayout emptyLabel;
    private LinearLayout footer;
    private boolean listaEmpty;
    private Button irCatalogo;
    private TextView tvImporteTotal, tvVacio;
    private float importeTotal = 0;
    private ArrayList<Producto> lista;
    private Lista lis;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this, R.layout.content_listado_carrito);
        emptyLabel = findViewById(R.id.emptyLabel);
        footer = findViewById(R.id.footer);
        lvLista = (SwipeMenuListView) findViewById(R.id.lista);
        tvVacio = (TextView) findViewById(R.id.texto_vacia);
        irCatalogo = (Button) findViewById(R.id.btn_ir_a_catalogo);
        tvImporteTotal = (TextView) findViewById(R.id.importeTotal);


        tvVacio.setText(R.string.empty_list);
        irCatalogo.setVisibility(View.GONE);
        footer.setVisibility(View.GONE);


        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lis = (Lista) getIntent().getSerializableExtra("objetoLista");
        lista = lis.getProductos();
        listaEmpty = lista.isEmpty();
        rellenarLista(lista);

        setTitle(lis.getNombre());


        //SwipeMenu
        SwipeMenuCreator creator = menuCreator();
        lvLista.setMenuCreator(creator);
        setOnClickSwipeMenu(lvLista);
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

    private void listaVacia(boolean vacia){

        if(vacia){
            emptyLabel.setVisibility(View.VISIBLE);
            lvLista.setVisibility(View.GONE);
            deshabilitarMenu();

        }else{
            emptyLabel.setVisibility(View.GONE);
            lvLista.setVisibility(View.VISIBLE);
        }
    }


    private void deshabilitarMenu(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
    }

    public void eliminarProducto(int position){
        Producto prod = (Producto)lvLista.getItemAtPosition(position);
        lista.remove(prod);
        Object[] params = {lis, prod};
        new BorrarProductoListaTask(this).execute(params);
        rellenarLista(lista);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        if(!listaEmpty){
            final MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_lista, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.renombrar:

                return true;

            case R.id.add_producto:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    public void setOnClickSwipeMenu(final SwipeMenuListView listView){

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0://delete
                        eliminarProducto(position);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
}
