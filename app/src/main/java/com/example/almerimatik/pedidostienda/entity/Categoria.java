package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Categoria {

    private long id;
    private String nombre;

    public Categoria() {

    }

    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
