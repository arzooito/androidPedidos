package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.asynTasks.CargarCatalogoTask;
import com.example.almerimatik.pedidostienda.dialogs.EditarCantidadDialog;
import com.example.almerimatik.pedidostienda.dialogs.ListaDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;


/**
 * Created by arzoo on 03/05/2018.
 */

public class ListaActivity extends ListadoProductoActivity {


    private boolean listaEmpty;
    private Button irCatalogo;
    private TextView tvVacio;
    private Lista lis;
    private Producto producto;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvVacio = (TextView) findViewById(R.id.texto_vacia);
        irCatalogo = (Button) findViewById(R.id.btn_ir_a_catalogo);
        tvImporteTotal = (TextView) findViewById(R.id.importeTotal);


        tvVacio.setText(R.string.empty_list);
        irCatalogo.setVisibility(View.GONE);
        footer.setVisibility(View.GONE);


        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                producto = (Producto)adapterView.getAdapter().getItem(position);
                EditarCantidadDialog dialog = EditarCantidadDialog.newInstance(ListaActivity.this,producto);
                dialog.show(getFragmentManager(), "EditarCantidadDialog");
            }
        });

        lis = (Lista) getIntent().getSerializableExtra("objetoLista");
        lista = lis.getProductos();
        listaEmpty = lista.isEmpty();
        rellenarLista(lista);

        setTitle(lis.getNombre());

        setOnClickSwipeMenu(lvLista);

    }

    public void eliminarProducto(int position){
        Producto prod = (Producto)lvLista.getItemAtPosition(position);
        lista.remove(prod);
        BD bd = new BD(this);
        bd.openBD(true);
        bd.eliminarProductoLista(lis, prod);
        bd.closeBD();
        rellenarLista(lista);
    }

    @Override
    public void editarProducto(int unidades){

        producto.setCantidad(unidades);
        rellenarLista(lista);
        BD bd = new BD(this);
        bd.openBD(true);
        bd.updateProductoLista(lis, producto);
        bd.closeBD();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.renombrar:
                ListaDialog dialog = new ListaDialog(lis);
                dialog.show(getFragmentManager(), "ListaDialog");
                return true;

            case R.id.add_producto:
                new CargarCatalogoTask(this,lis).execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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


    @Override
    public void botonVolver(){

    }

}
