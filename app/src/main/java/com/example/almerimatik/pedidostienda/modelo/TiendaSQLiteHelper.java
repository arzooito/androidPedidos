package com.example.almerimatik.pedidostienda.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.almerimatik.pedidostienda.constantes.Data.VERSION_DB;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class TiendaSQLiteHelper extends SQLiteOpenHelper {


    public TiendaSQLiteHelper(final Context context) {
        super(context, "BDTienda", null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE producto(id INTEGER PRIMARY KEY, nombre TEXT, formato TEXT, precio REAL, foto TEXT, idMarca INTEGER, idSubcategoria INTEGER)");
        db.execSQL("CREATE TABLE marca(id INTEGER PRIMARY KEY, nombre TEXT)");
        db.execSQL("CREATE TABLE categoria(id INTEGER PRIMARY KEY, nombre TEXT)");
        db.execSQL("CREATE TABLE subcategoria(id INTEGER PRIMARY KEY, nombre TEXT, idCategoria INTEGER)");

        db.execSQL("CREATE TABLE lista(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)");
        db.execSQL("CREATE TABLE pedido(_id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT, fechaRecogida TEXT)");
        db.execSQL("CREATE TABLE lista_producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, idLista INTEGER, idProducto INTEGER, unidades INTEGER)");
        db.execSQL("CREATE TABLE pedido_producto(_id INTEGER PRIMARY KEY AUTOINCREMENT, idPedido INTEGER, idProducto INTEGER, unidades INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
