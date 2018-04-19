package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Subcategoria implements Serializable{

    private long id;
    private String nombre;
    private Categoria categoria;

    public Subcategoria(){

    }

    public Subcategoria(long id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", id);
        nuevoRegistro.put("nombre", nombre);
        nuevoRegistro.put("idCategoria", categoria.getId());
        return nuevoRegistro;
    }

    public String[] getCampos(){

        String[] campos = {"id","nombre", "idCategoria"};
        return campos;
    }
}
