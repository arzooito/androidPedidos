package com.example.almerimatik.pedidostienda.entity;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Producto implements Parcelable, Serializable {

    private long id;
    private String nombre;
    private String formato;
    private float precio;
    private String foto;
    private Marca marca;
    private Subcategoria subcategoria;
    private int cantidad = 0;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ContentValues rellenar(){

        final ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", id);
        nuevoRegistro.put("nombre", nombre);
        nuevoRegistro.put("formato", formato);
        nuevoRegistro.put("precio", precio);
        nuevoRegistro.put("foto", foto);
        nuevoRegistro.put("idMarca", marca.getId());
        nuevoRegistro.put("idSubcategoria", subcategoria.getId());
        return nuevoRegistro;
    }

    public String[] getCampos(){

        String[] campos = {"id","nombre", "formato", "precio", "foto", "idMarca", "idSubcategoria"};
        return campos;
    }

    public String getRutaFoto(){

        String rutaFoto =
                "productos/"+subcategoria.getCategoria().getNombre()+"/"+subcategoria.getNombre()+"/"+marca.getNombre()+"/"+foto;

        return rutaFoto;
    }


    /*
    * Parceable
    */


    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(final Parcel in)
        {
            return new Producto().readFromParcel(in);
        }

        @Override
        public Producto[] newArray(final int size)
        {
            return new Producto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    private Producto readFromParcel(final Parcel in) {

        id = in.readLong();
        nombre = in.readString();
        formato = in.readString();
        precio = in.readFloat();
        foto = in.readString();
        marca = (Marca) in.readSerializable();
        subcategoria = (Subcategoria) in.readSerializable();
        cantidad = in.readInt();

        return this;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeLong(id);
        out.writeString(nombre);
        out.writeString(formato);
        out.writeFloat(precio);
        out.writeString(foto);
        out.writeSerializable(marca);
        out.writeSerializable(subcategoria);
        out.writeInt(cantidad);

    }
}
