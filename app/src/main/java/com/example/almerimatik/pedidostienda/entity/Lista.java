package com.example.almerimatik.pedidostienda.entity;

import java.util.List;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Lista {

    private long id;
    private String nombre;
    private List<Producto> productos;
    private boolean pedido;

    public Lista(){

        this.pedido = false;
    }

    public Lista(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.pedido = false;
    }

    public Lista(long id, String nombre, List<Producto> productos, boolean pedido) {
        this.id = id;
        this.nombre = nombre;
        this.productos = productos;
        this.pedido = pedido;
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

    public boolean isPedido() {
        return pedido;
    }

    public void setPedido(boolean pedido) {
        this.pedido = pedido;
    }
}
