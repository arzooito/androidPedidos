package com.example.almerimatik.pedidostienda.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Rutas;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.entity.Producto;

import java.util.ArrayList;


/**
 * Created by arzoo on 18/03/2018.
 */

public class CatalogoAdapter extends ArrayAdapter<Producto>{

    static final int LAYOUT = R.layout.item_lista_catalogo;
    static final String RUTA = Rutas.URL_BASE+"/tienda-img/";
    ProductoHolder holder;
    Producto prod;
    int layout = LAYOUT;

    public CatalogoAdapter(final Context context) {
        super(context, LAYOUT);
    }
    public CatalogoAdapter(final Context context, int layout) {
        super(context, layout);
        this.layout = layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View celda = convertView;

        if (celda == null) {

            final LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            celda = inflater.inflate(layout, parent, false);

            holder = new ProductoHolder();
            holder.webFoto = (WebView) celda.findViewById(R.id.webFoto);
            holder.labelNombre = (TextView) celda.findViewById(R.id.labelNombre);
            holder.labelMarca = (TextView) celda.findViewById(R.id.labelMarca);
            holder.labelFormato = (TextView) celda.findViewById(R.id.labelFormato);
            holder.labelPrecio = (TextView) celda.findViewById(R.id.labelPrecio);
            holder.labelCantidad = (TextView) celda.findViewById(R.id.tvCantidadItemProd);
            holder.btnSumar = (ImageButton) celda.findViewById(R.id.btnAddCantidad);
            holder.btnRestar = (ImageButton) celda.findViewById(R.id.btnRmCantidad);
            holder.btnAddCarrito = (ImageButton) celda.findViewById(R.id.btnAddCarrito);
            holder.cantidad = 1;

            celda.setTag(holder);

        } else {
            holder = (ProductoHolder) celda.getTag();
        }

        prod = getItem(position);
        String urlFoto = RUTA + prod.getRutaFoto().replace(" ", "%20");
        holder.webFoto.loadUrl(urlFoto);
        holder.webFoto.getSettings().setLoadWithOverviewMode(true);
        holder.webFoto.getSettings().setUseWideViewPort(true);
        holder.labelNombre.setText(prod.getNombre());
        holder.labelMarca.setText(prod.getMarca().getNombre());
        holder.labelFormato.setText(prod.getFormato());
        holder.labelPrecio.setText(String.format("%.2f€",prod.getPrecio()));
        holder.labelCantidad.setText(String.format("%d",holder.cantidad));

        final View CELDA = celda;

        holder.btnAddCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToCarrito(position, CELDA);
            }
        });

        holder.btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumarCantidad(CELDA);
            }
        });

        holder.btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restarCantidad(CELDA);
            }
        });

        return celda;
    }


    public void sumarCantidad(View celda){
        holder = (ProductoHolder) celda.getTag();

        holder.cantidad += 1;
        holder.labelCantidad.setText(String.format("%d",holder.cantidad));
    }

    public void restarCantidad(View celda){

        holder = (ProductoHolder) celda.getTag();

        if(holder.cantidad > 1){
            holder.cantidad -= 1;
        }
        holder.labelCantidad.setText(String.format("%d",holder.cantidad));

    }


    public void addToCarrito(int pos, View celda){
        ArrayList<Producto> carrito = Sesion.getCarrito();
        boolean enCarrito = false;
        int cant = 0;

        holder = (ProductoHolder) celda.getTag();
        prod = getItem(pos);

        for(Producto reg : carrito){
            if(reg.getId() == prod.getId()){
                cant = reg.getCantidad() + holder.cantidad;
                reg.setCantidad(cant);
                enCarrito = true;
            }
        }

        if(!enCarrito){
            prod.setCantidad(holder.cantidad);
            carrito.add(prod);
        }

        String msg = prod.getNombre() + " x " + holder.cantidad + " añadadido";
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

        holder.cantidad = 1;
        holder.labelCantidad.setText(String.format("%d",holder.cantidad));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////      HOLDER      /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    static class ProductoHolder {

        WebView webFoto;
        TextView labelNombre, labelMarca, labelFormato, labelPrecio, labelCantidad;
        ImageButton btnSumar, btnRestar, btnAddCarrito;
        int cantidad;
    }
}
