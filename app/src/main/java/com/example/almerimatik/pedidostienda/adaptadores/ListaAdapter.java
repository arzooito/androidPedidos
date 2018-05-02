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
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.ArrayList;

/**
 * Created by arzoo on 01/05/2018.
 */

public class ListaAdapter extends ArrayAdapter<Lista> {

    static final int LAYOUT = R.layout.item_lista_listas;
    ListaAdapter.ListaHolder holder;
    Lista lis;

    public ListaAdapter(final Context context) {
        super(context, LAYOUT);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View celda = convertView;

        if (celda == null) {
            final LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            celda = inflater.inflate(LAYOUT, parent, false);

            holder = new ListaAdapter.ListaHolder();
            holder.tvNombre = (TextView) celda.findViewById(R.id.tvNombre);
            holder.tvCantidad = (TextView) celda.findViewById(R.id.tvCantidad);
            holder.tvImporteTotal = (TextView) celda.findViewById(R.id.tvImporteTotal);

            celda.setTag(holder);

        } else {
            holder = (ListaAdapter.ListaHolder) celda.getTag();
        }

        lis = getItem(position);
        float importe = calcularImporte(lis.getProductos());

        holder.tvNombre.setText(lis.getNombre());
        holder.tvCantidad.setText(String.format("%d", lis.getProductos().size()));
        holder.tvImporteTotal.setText(String.format("%.2f €",importe));

        return celda;
    }

    public float calcularImporte(ArrayList<Producto> prods){

        float importe = 0;

        for(Producto reg : prods){
            importe += reg.getPrecio() * reg.getCantidad();
        }
        return importe;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////      HOLDER      /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    static class ListaHolder {

        TextView tvNombre, tvCantidad, tvImporteTotal;
    }
}
