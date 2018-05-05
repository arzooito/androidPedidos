package com.example.almerimatik.pedidostienda.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;

import java.util.ArrayList;
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

    public ArrayList<Producto> cargarProductos(){

        ArrayList<Producto> lista = Modelo.cargarProductos(db,context);
        return lista;
    }

    public void guardarProductos(List<Producto> productos){

        for(Producto prod : productos){
            Producto producto = Modelo.cargarProducto(db,context,prod.getId());
            if(producto.getId() == 0){
                Modelo.guardarProducto(context,db,prod);
            }else{
                Modelo.updateProducto(context,db,prod);
            }

        }
    }

    public void eliminarProductosSalen(String[] ids){

        if(ids != null){

            for(int i=0; i < ids.length; i++){
                Modelo.eliminarProducto(context,db, ids[i]);
            }
        }
    }

    public void eliminarMarcasObsoletas(){

        int ids[] = Modelo.cargarMarcasSinProducto(db,context);

        if(ids != null){

            for(int i=0; i < ids.length; i++){
                String id = String.format("%d",ids[i]);
                Modelo.eliminarMarca(context,db, id);
            }
        }
    }

    public void eliminarSubcategoriasObsoletas(){

        int ids[] = Modelo.cargarSubcategoriasSinProducto(db,context);

        if(ids != null){

            for(int i=0; i < ids.length; i++){
                String id = String.format("%d",ids[i]);
                Modelo.eliminarSubcategoria(context,db, id);
            }
        }
    }

    public void eliminarCategoriasObsoletas(){

        int ids[] = Modelo.cargarCategoriasSinSubcategoria(db,context);

        if(ids != null){

            for(int i=0; i < ids.length; i++){
                String id = String.format("%d",ids[i]);
                Modelo.eliminarSubcategoria(context,db, id);
            }
        }
    }

    public void eliminarObsoletos(){

        eliminarMarcasObsoletas();
        eliminarSubcategoriasObsoletas();
        eliminarCategoriasObsoletas();
    }

    public void guardarPedido(Pedido ped){

        ArrayList<Producto> prods = ped.getProductos();
        long id = Modelo.guardarPedido(context, db, ped);
        ped.setId(id);
        for(Producto reg : prods){
            Modelo.guardarProductosPedido(context, db, ped, reg);
        }
    }

    public Pedido cargarPedido(long id){

        Pedido pedido = Modelo.cargarPedido(db,context, id);
        return pedido;
    }

    public ArrayList<Pedido> cargarPedidos(){

        ArrayList<Pedido> pedidos = Modelo.cargarPedidos(db, context);
        return pedidos;
    }

    public void guardarLista(Lista lista){

        ArrayList<Producto> prods = lista.getProductos();
        long id = Modelo.guardarLista(context, db, lista);
        lista.setId(id);
        for(Producto reg : prods){
            Modelo.guardarProductosLista(context, db, lista, reg);
        }
    }

    public Lista cargarLista(long id){

        Lista lista = Modelo.cargarLista(db,context, id);
        return lista;
    }

    public ArrayList<Lista> cargarListas(){

        ArrayList<Lista> listas = Modelo.cargarListas(db, context);
        return listas;
    }

    public void eliminarLista(Lista lista){
        Modelo.eliminarProductosLista(context, db, lista.getId());
        Modelo.eliminarLista(context, db, lista.getId());
    }

    public void eliminarProductoLista(Lista lis, Producto prod){

        Modelo.eliminarProductoLista(context, db, lis.getId(), prod.getId());
    }

    public void updateProductoLista(Lista lis, Producto prod){
        Modelo.updateProductoLista(context, db, lis.getId(), prod.getId(), prod.getCantidad());
    }
}
