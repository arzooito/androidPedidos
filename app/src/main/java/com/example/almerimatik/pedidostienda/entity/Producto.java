package com.example.almerimatik.pedidostienda.entity;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Producto {

    private long id;
    private String nombre;
    private String formato;
    private float precio;
    private String foto;
    private Marca marca;
    private Subcategoria subcategoria;

    public Producto(){

    }

    public Producto(long id, String nombre, String formato, float precio, String foto, Marca marca, Subcategoria subcategoria) {
        this.id = id;
        this.nombre = nombre;
        this.formato = formato;
        this.precio = precio;
        this.foto = foto;
        this.marca = marca;
        this.subcategoria = subcategoria;
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
}
