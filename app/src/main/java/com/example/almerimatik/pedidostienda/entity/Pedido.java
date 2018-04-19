package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.Date;
import java.util.Map;

/**
 * Created by Almerimatik on 12/02/2018.
 */

public class Pedido {

    long id;
    Date fecha;
    private Map<Producto,Integer> productos;

    public Pedido(){

    }

    public Pedido(long id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        String sFecha = Fechas.FormatearFecha(fecha);
        nuevoRegistro.put("id", id);
        nuevoRegistro.put("fecha", sFecha);
        return nuevoRegistro;

    }

    public String[] getCampos(){

        String[] campos = {"id","fecha"};
        return campos;
    }
}
