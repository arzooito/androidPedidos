package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;
import android.os.Parcelable;

import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by Almerimatik on 12/02/2018.
 */

public class Pedido implements Serializable {

    long id;
    Date fecha;
    Date fechaRecogida;
    Date horaRecogida;
    long superID;
    private ArrayList<Producto> productos;

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

    public Date getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(Date fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public Date getHoraRecogida() {
        return horaRecogida;
    }

    public void setHoraRecogida(Date horaRecogida) {
        this.horaRecogida = horaRecogida;
    }

    public long getSuperID() {
        return superID;
    }

    public void setSuperID(long superID) {
        this.superID = superID;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        String sFecha = Fechas.FormatearFecha(fecha);
        String sFechaRecogida = Fechas.FormatearFecha(fechaRecogida);
        String sHoraRecogida = Fechas.FormatearHora(fechaRecogida);
        nuevoRegistro.put("idUsuario", Sesion.getIdUsuario());
        nuevoRegistro.put("fecha", sFecha);
        nuevoRegistro.put("fechaRecogida", sFechaRecogida);
        nuevoRegistro.put("horaRecogida", sHoraRecogida);
        nuevoRegistro.put("id", superID);
        return nuevoRegistro;

    }

    public String[] getCampos(){

        String[] campos = {"_id", "fecha", "fechaRecogida", "horaRecogida", "id" };
        return campos;
    }
}
