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
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.ArrayList;

/**
 * Created by arzoo on 01/05/2018.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    static final int LAYOUT = R.layout.item_lista_pedido;
    PedidoAdapter.PedidoHolder holder;
    Pedido ped;

    public PedidoAdapter(final Context context) {
        super(context, LAYOUT);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View celda = convertView;

        if (celda == null) {
            final LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            celda = inflater.inflate(LAYOUT, parent, false);

            holder = new PedidoAdapter.PedidoHolder();
            holder.labelId = (TextView) celda.findViewById(R.id.idPedido);
            holder.labelFecha = (TextView) celda.findViewById(R.id.fecha);
            holder.labelCantidad = (TextView) celda.findViewById(R.id.cantidadProductos);
            holder.labelImporte = (TextView) celda.findViewById(R.id.importeTotal);
            holder.labelFechaRecogida = (TextView) celda.findViewById(R.id.fechaRecogida);
            holder.labelHoraRecogida = (TextView) celda.findViewById(R.id.horaRecogida);

            celda.setTag(holder);

        } else {
            holder = (PedidoAdapter.PedidoHolder) celda.getTag();
        }

        ped = getItem(position);
        float importe = calcularImporte(ped.getProductos());

        holder.labelId.setText(String.format("%05d", ped.getSuperID()));
        holder.labelFecha.setText(Fechas.FormatearFecha(ped.getFecha()));
        holder.labelCantidad.setText(String.format("%d", ped.getProductos().size()));
        holder.labelImporte.setText(String.format("%.2f €",importe));
        holder.labelFechaRecogida.setText(Fechas.FormatearFecha(ped.getFechaRecogida(), Fechas.FORMATO_FECHA_CERCANA));
        holder.labelHoraRecogida.setText(Fechas.FormatearHora(ped.getHoraRecogida()));

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

    static class PedidoHolder {

        TextView labelId, labelFecha, labelCantidad, labelImporte, labelFechaRecogida, labelHoraRecogida;
    }
}
