package com.example.almerimatik.pedidostienda.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.dialogs.EditarCantidadDialog;
import com.example.almerimatik.pedidostienda.entity.Producto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 16/04/2018.
 */

public class CarritoAdapter extends ArrayAdapter<Producto> implements Serializable {


    static final int LAYOUT = R.layout.item_lista_carrito;
    CarritoAdapter.ProductoHolder holder;
    Producto prod;
    Activity context;
    int index;

    public CarritoAdapter(final Context context) {
        super(context, LAYOUT);
        this.context = (Activity)context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View celda = convertView;
        this.index = position;

        if (celda == null) {
            final LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            celda = inflater.inflate(LAYOUT, parent, false);

            holder = new CarritoAdapter.ProductoHolder();
            holder.labelNombre = (TextView) celda.findViewById(R.id.labelNombre);
            holder.labelMarca = (TextView) celda.findViewById(R.id.labelMarca);
            holder.labelFormato = (TextView) celda.findViewById(R.id.labelFormato);
            holder.labelPrecio = (TextView) celda.findViewById(R.id.labelPrecio);
            holder.labelPrecioTotal = (TextView) celda.findViewById(R.id.labelPrecioTotal);
            holder.labelCantidad = (TextView) celda.findViewById(R.id.labelUnidades);

            celda.setTag(holder);

        } else {
            holder = (CarritoAdapter.ProductoHolder) celda.getTag();
        }

        prod = getItem(position);
        holder.labelNombre.setText(prod.getNombre());
        holder.labelMarca.setText(prod.getMarca().getNombre());
        holder.labelFormato.setText(prod.getFormato());
        holder.labelPrecio.setText(String.format("%.2f € unidad",prod.getPrecio()));
        holder.labelCantidad.setText(String.format("x %d",prod.getCantidad()));
        float total = prod.getCantidad() * prod.getPrecio();
        holder.labelPrecioTotal.setText(String.format("%.2f €", total));

        final View CELDA = celda;

        return celda;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////      HOLDER      /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    static class ProductoHolder {

        TextView labelNombre, labelMarca, labelFormato, labelPrecio, labelCantidad, labelPrecioTotal;

    }
}
