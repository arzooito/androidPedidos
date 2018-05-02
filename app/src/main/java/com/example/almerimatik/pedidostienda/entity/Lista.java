package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

import com.example.almerimatik.pedidostienda.constantes.Sesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Lista implements Serializable{

    private long id;
    private String nombre;
    private ArrayList<Producto> productos;

    public Lista(){

    }

    public Lista(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Lista(long id, String nombre, ArrayList<Producto> productos) {
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

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("idUsuario", Sesion.getIdUsuario());
        nuevoRegistro.put("nombre", nombre);
        return nuevoRegistro;

    }

    public String[] getCampos(){

        String[] campos = {"_id", "nombre"};
        return campos;
    }
}
