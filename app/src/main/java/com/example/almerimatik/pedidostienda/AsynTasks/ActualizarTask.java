package com.example.almerimatik.pedidostienda.AsynTasks;

import android.os.AsyncTask;
import android.view.View;

import com.example.almerimatik.pedidostienda.Modelo.BD;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.Tools.Fechas;
import com.example.almerimatik.pedidostienda.Tools.Msg;
import com.example.almerimatik.pedidostienda.Tools.XML;
import com.example.almerimatik.pedidostienda.activity.MainActivity;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;
import com.example.almerimatik.pedidostienda.ws.Ws;

import org.w3c.dom.Document;

import java.util.List;

/**
 * Created by arzoo on 10/02/2018.
 */

public class ActualizarTask extends AsyncTask<Void, Integer, Void> {

    MainActivity main;

    public ActualizarTask(MainActivity main){
        this.main = main;
    }

    protected void onPreExecute(){

        main.getTvProgress().setText(R.string.progress_actualizacion);
        main.getProgressBar().setVisibility(View.VISIBLE);
    }

    protected Void doInBackground(Void... params) {

        actualizar();
        return null;
    }

    protected void onProgressUpdate(String... values) {

        main.getTvProgress().setText(values[0]);
    }

    protected void onPostExecute(Void result) {

        main.getProgressBar().setVisibility(View.GONE);
        main.iniciar();
    }

    private void actualizar(){

        String fecha = Data.getUltimaActualizacion(main);
        BD bd = new BD(main);
        String xml = Ws.actualizar(fecha);
        Document doc = XML.getDocumento(xml);

        if(doc != null){

            bd.openBD(true);

            String[] salen = XML.productosSalen(doc);
            bd.eliminarProductosSalen(salen);

            List<Marca> marcas = XML.getMarcasEntran(doc);
            bd.guardarMarcas(marcas);

            List<Categoria> categorias = XML.getCategoriasEntran(doc);
            bd.guardarCategorias(categorias);

            List<Subcategoria> subcategorias = XML.getSubcategoriasEntran(doc,bd);
            bd.guardarSubcategorias(subcategorias);

            List<Producto> entran = XML.getProductosEntran(doc,bd);
            bd.guardarProductos(entran);

            bd.closeBD();

            String actualizacionFecha = Fechas.FormatearFechaHoraSQL();
            Data.setUltimaActualizacion(main,actualizacionFecha);
        }else{
            String msj = main.getString(R.string.conection_ws_error);
            Msg.toast(main,msj);
        }

    }
}
