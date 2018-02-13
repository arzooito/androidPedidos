package com.example.almerimatik.pedidostienda.entity;

import java.util.List;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Lista {

    private long id;
    private String nombre;
    private List<Producto> productos;

    public Lista(){

    }

    public Lista(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Lista(long id, String nombre, List<Producto> productos) {
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
