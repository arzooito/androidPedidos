package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.dialogs.ListaDialog;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Contenido;
import com.example.almerimatik.pedidostienda.tools.Msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Almerimatik on 06/02/2018.
 */

public class CarritoActivity extends BaseActivity{

    private SwipeMenuListView lvLista;
    private CarritoAdapter adapter;
    private LinearLayout emptyLabel;
    private LinearLayout footer;
    private boolean listaEmpty;
    private Button irCatalogo, realizarPedido;
    private TextView tvImporteTotal;
    private float importeTotal = 0;
    private ArrayList<Producto> lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Contenido.addContent(this, R.layout.content_listado_carrito);
        emptyLabel = findViewById(R.id.emptyLabel);
        footer = findViewById(R.id.footer);
        lvLista = (SwipeMenuListView) findViewById(R.id.lista);
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
                AbrirRealizarPedido();
            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lista = (ArrayList<Producto>) getIntent().getSerializableExtra("lista");
        listaEmpty = lista.isEmpty();
        rellenarLista(lista);

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
            footer.setVisibility(View.GONE);
            deshabilitarMenu();

        }else{
            emptyLabel.setVisibility(View.GONE);
            lvLista.setVisibility(View.VISIBLE);
            footer.setVisibility(View.VISIBLE);
        }
    }

    public void limpiarCarrito(){

        Sesion.getCarrito().clear();
        adapter.clear();
        listaVacia(true);
    }

    private void deshabilitarMenu(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
    }


    private void guardarCarrito(){
        ListaDialog dialog = new ListaDialog();
        dialog.show(getFragmentManager(), "ListaDialog");
    }

    private void AbrirRealizarPedido(){
        Intent intent = new Intent(this, RealizarPedidoActivity.class);
        ArrayList<Producto> lista = Sesion.getCarrito();
        intent.putExtra("lista", (Serializable) lista);
        intent.putExtra("importeTotal", importeTotal);
        startActivity(intent);

    }

    public void eliminarProducto(int position){
        Producto prod = (Producto)lvLista.getItemAtPosition(position);
        lista.remove(prod);
        Sesion.getCarrito().remove(position);
        rellenarLista(lista);
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
                Msg.preguntarLimpiarCarrito(this);
                return true;

            case R.id.guardar_en_lista:
                guardarCarrito();
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
