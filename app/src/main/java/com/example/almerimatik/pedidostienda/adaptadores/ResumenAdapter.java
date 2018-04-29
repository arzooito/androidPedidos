package com.example.almerimatik.pedidostienda.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.entity.Producto;

/**
 * Created by arzoo on 26/04/2018.
 */

public class ResumenAdapter extends ArrayAdapter<Producto> {

    static final int LAYOUT = R.layout.item_lista_resumen;
    ResumenAdapter.ProductoHolder holder;
    Producto prod;

    public ResumenAdapter(final Context context) {
        super(context, LAYOUT);
    }

    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View celda = convertView;

        if (celda == null) {
            final LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            celda = inflater.inflate(LAYOUT, parent, false);

            holder = new ResumenAdapter.ProductoHolder();
            holder.tvNombre = (TextView) celda.findViewById(R.id.tvNombre);
            holder.tvSubtotal = (TextView) celda.findViewById(R.id.tvSubtotal);
            holder.tvCantidad = (TextView) celda.findViewById(R.id.tvCantidad);

            celda.setTag(holder);

        } else {
            holder = (ResumenAdapter.ProductoHolder) celda.getTag();
        }

        prod = getItem(position);
        holder.tvNombre.setText(prod.getNombre());
        holder.tvCantidad.setText(String.format("x %d",prod.getCantidad()));
        float total = prod.getCantidad() * prod.getPrecio();
        holder.tvSubtotal.setText(String.format("%.2f €", total));

        return celda;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////      HOLDER      /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    static class ProductoHolder {

        TextView tvNombre, tvCantidad, tvSubtotal;
    }
}
