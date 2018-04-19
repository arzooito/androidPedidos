package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Lista {

    private long id;
    private String nombre;
    private Map<Producto,Integer> productos;

    public Lista(){

    }

    public Lista(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Lista(long id, String nombre, Map<Producto,Integer> productos) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<Producto,Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<Producto,Integer> productos) {
        this.productos = productos;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", id);
        nuevoRegistro.put("nombre", nombre);
        return nuevoRegistro;

    }

    public String[] getCampos(){

        String[] campos = {"id","nombre"};
        return campos;
    }
}
