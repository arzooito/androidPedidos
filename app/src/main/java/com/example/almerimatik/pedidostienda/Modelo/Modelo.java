package com.example.almerimatik.pedidostienda.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.almerimatik.pedidostienda.Tools.Msg;
import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;

import java.util.ArrayList;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class Modelo {

    private static final String PRODUCTO = "producto";
    private static final String MARCA = "marca";
    private static final String CAT = "categoria";
    private static final String SUB = "subcategoria";
    private static final String LISTA = "lista";
    private static final String LIS_PROD = "lista_producto";
    private static final String PEDIDO = "pedido";
    private static final String PED_PROD = "pedido_producto";


    ///// MARCA ////////////////////

    public static void guardarMarca(Context context, SQLiteDatabase db, Marca marca){

        String tabla = MARCA;

        try {
            final ContentValues valores = marca.rellenar();
            db.insertOrThrow(tabla, null, valores);
        } catch (final Exception e) {
            Log.d("policia", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static Marca cargarMarca(SQLiteDatabase db, Context context,String id){

        Marca marca = new Marca();

        String tabla = MARCA;
        String[] campos = marca.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                marca.setId(c.getLong(0));
                marca.setNombre(c.getString(1));
            }


        }
        c.close();
        return marca;
    }

    ///////// CATEGORIA ////////

    public static void guardarCategoria(Context context, SQLiteDatabase db, Categoria cat){

        String tabla = CAT;

        try {
            final ContentValues valores = cat.rellenar();
            db.insertOrThrow(tabla, null, valores);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static Categoria cargarCategoria(SQLiteDatabase db, Context context, String id){

        Categoria cat = new Categoria();

        String tabla = CAT;
        String[] campos = cat.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                cat.setId(c.getLong(0));
                cat.setNombre(c.getString(1));
            }
        }

        c.close();
        return cat;
    }

    /////// Subcategoria ////////////////////

    public static void guardarSubcategoria(Context context, SQLiteDatabase db, Subcategoria sub){

        String tabla = SUB;

        try {
            final ContentValues valores = sub.rellenar();
            db.insertOrThrow(tabla, null, valores);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }


    public static Subcategoria cargarSubcategoria(SQLiteDatabase db, Context context, String id){

        Subcategoria sub = new Subcategoria();

        String tabla = SUB;
        String[] campos = sub.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                sub.setId(c.getLong(0));
                sub.setNombre(c.getString(1));

                String idCat = c.getString(2);
                Categoria cat = Modelo.cargarCategoria(db, context, idCat);
                sub.setCategoria(cat);
            }
        }

        c.close();
        return sub;
    }

    //////////////////// Productos /////////////////////////////////

    public static void guardarProducto(Context context, SQLiteDatabase db, Producto prod){

        String tabla = PRODUCTO;

        try {
            final ContentValues valores = prod.rellenar();
            db.insertOrThrow(tabla, null, valores);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static void eliminarProducto(Context context, SQLiteDatabase db, String id){

        String tabla = PRODUCTO;
        try {
            String[] args = new String[]{id};
            String where = "id = ?";
            db.delete(tabla,where,args);
        } catch (final Exception e) {
            Log.d("tienda", "Error al eliminar de BD: ", e);
            Msg.mensaje(context, "Error", "Error al eliminar de BD: " + e.getMessage(), false);
        }
    }
}
