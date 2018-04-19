package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Marca implements Serializable{

    private long id;
    private String nombre;

    public Marca(){

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
