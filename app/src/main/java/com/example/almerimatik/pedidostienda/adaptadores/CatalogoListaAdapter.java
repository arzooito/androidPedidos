package com.example.almerimatik.pedidostienda.adaptadores;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.CatalogoAListaActivity;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;

import java.util.ArrayList;

/**
 * Created by arzoo on 06/05/2018.
 */

public class CatalogoListaAdapter extends CatalogoAdapter {

    static final int LAYOUT = R.layout.item_lista_catalogo_a_lista;

    Lista lis;
    Context act;

    public CatalogoListaAdapter(Context context) {
        super(context,LAYOUT);
    }

    public CatalogoListaAdapter(Context context, Lista lista) {
        super(context,LAYOUT);
        this.act = (CatalogoAListaActivity) context;
        this.lis = lista;
    }

    @Override
    public void addToCarrito(int pos, View celda) {
        ArrayList<Producto> listaProductos = lis.getProductos();
        boolean enListaProductos = false;
        int cant = 0;

        holder = (ProductoHolder) celda.getTag();
        prod = getItem(pos);

        for (Producto reg : listaProductos) {
            if (reg.getId() == prod.getId()) {
                cant = reg.getCantidad() + holder.cantidad;
                reg.setCantidad(cant);
                enListaProductos = true;
                actualizar(reg);
            }
        }

        if(!enListaProductos){
            prod.setCantidad(holder.cantidad);
            lis.getProductos().add(prod);
            guardar(prod);
        }

        String msg = prod.getNombre() + " x " + holder.cantidad + " añadadido";
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        holder.cantidad = 1;
        holder.labelCantidad.setText(String.format("%d",holder.cantidad));
    }

    public void actualizar(Producto producto){
        BD bd = new BD(act);
        bd.openBD(true);
        bd.updateProductoLista(lis, producto);
        bd.closeBD();
    }

    public void guardar(Producto producto){
        BD bd = new BD(act);
        bd.openBD(true);
        bd.guardarProductoLista(lis, prod);
        bd.closeBD();
    }
}
