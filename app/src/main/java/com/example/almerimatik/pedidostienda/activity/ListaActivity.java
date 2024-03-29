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
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.CatalogoAdapter;
import com.example.almerimatik.pedidostienda.asynTasks.CargarCatalogoTask;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.dialogs.EditarCantidadDialog;
import com.example.almerimatik.pedidostienda.dialogs.ListaDialog;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;

import java.util.ArrayList;


/**
 * Created by arzoo on 03/05/2018.
 */

public class ListaActivity extends ListadoProductoActivity {


    private boolean listaEmpty;
    private Button irCatalogo, btnAgregar;
    private TextView tvVacio;
    private Lista lis;
    private Producto producto;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvVacio = (TextView) findViewById(R.id.texto_vacia);
        irCatalogo = (Button) findViewById(R.id.btn_ir_a_catalogo);
        tvImporteTotal = (TextView) findViewById(R.id.importeTotal);
        btnAgregar = (Button) findViewById(R.id.btn_realizar_pedido);
        btnAgregar.setText(R.string.agregar_a_carrito);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarProductosACarrito();
            }
        });

        tvVacio.setText(R.string.empty_list);
        irCatalogo.setVisibility(View.GONE);


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

    public void agregarProductosACarrito(){

        int cant = 0;

        for(int n = 0; n < lista.size(); n++){

            Producto prod = lista.get(n);
            ArrayList<Producto> carrito = Sesion.getCarrito();
            boolean enCarrito = false;
            for(Producto reg : carrito){
                if(reg.getId() == prod.getId()){
                    cant = reg.getCantidad() + prod.getCantidad();
                    reg.setCantidad(cant);
                    enCarrito = true;
                }
            }

            if(!enCarrito){
                Sesion.getCarrito().add(prod);
            }
        }

        Toast.makeText(this, R.string.agregados_productos, Toast.LENGTH_SHORT).show();
        abrirCarrito();
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

}
