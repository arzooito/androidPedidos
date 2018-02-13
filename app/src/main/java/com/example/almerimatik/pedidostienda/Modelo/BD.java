package com.example.almerimatik.pedidostienda.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

}
