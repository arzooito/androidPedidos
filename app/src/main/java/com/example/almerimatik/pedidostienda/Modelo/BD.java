package com.example.almerimatik.pedidostienda.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;

import java.util.List;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class BD {

    private Context context;
    private SQLiteDatabase db;
    private TiendaSQLiteHelper helper;

    public BD(Context context){
        this.context = context;
    }

    public void setBD(SQLiteDatabase db){this.db = db;}

    public SQLiteDatabase getBD(){return this.db;}

    public void openBD(boolean writable){

        helper = new TiendaSQLiteHelper(context);

        if(writable){
            db = helper.getWritableDatabase();
        }else{
            db = helper.getReadableDatabase();
        }
    }

    public void closeBD(){
        db.close();
        helper.close();
    }

    public Marca getMarca(String id){

        Marca marca = Modelo.cargarMarca(db,context,id);
        return marca;
    }

    public Categoria getCategoria(String id){

        Categoria cat= Modelo.cargarCategoria(db,context,id);
        return cat;
    }

    public Subcategoria getSubcategoria(String id){

        Subcategoria sub= Modelo.cargarSubcategoria(db,context,id);
        return sub;
    }

    public void guardarMarcas(List<Marca> marcas){

        for(Marca marca : marcas){
            Modelo.guardarMarca(context,db,marca);
        }
    }

    public void guardarCategorias(List<Categoria> categorias){

        for(Categoria cat : categorias){
            Modelo.guardarCategoria(context,db,cat);
        }
    }

    public void guardarSubcategorias(List<Subcategoria> subcategorias){

        for(Subcategoria sub : subcategorias){
            Modelo.guardarSubcategoria(context,db,sub);
        }
    }

    public void guardarProductos(List<Producto> productos){

        for(Producto prod : productos){
            Modelo.guardarProducto(context,db,prod);
        }
    }

    public void eliminarProductosSalen(String[] ids){

        if(ids != null){

            for(int i=0; i < ids.length; i++){
                Modelo.eliminarProducto(context,db, ids[i]);
            }
        }
    }
}
