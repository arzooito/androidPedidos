package com.example.almerimatik.pedidostienda.constantes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class Data {

    public static final int VERSION_DB = 1;
    public static final String SHARED_NAME = "datos";

    public static SharedPreferences getShared(Activity act){

        SharedPreferences shared=act.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return shared;
    }

    public static void setUltimaActualizacion(Activity act, String valor){

        SharedPreferences.Editor editor = getShared(act).edit();
        editor.putString("ultimaActualizacion", valor);
        editor.commit();
    }

    public static void setNombreUsuario(Activity act, String valor){

        SharedPreferences.Editor editor = getShared(act).edit();
        editor.putString("nombreUsuario", valor);
        editor.commit();
    }

    public static void setIdUsuario(Activity act, long valor){

        SharedPreferences.Editor editor = getShared(act).edit();
        editor.putLong("idUsuario", valor);
        editor.commit();
    }

    public static void setPassword(Activity act, String valor){

        SharedPreferences.Editor editor = getShared(act).edit();
        editor.putString("password", valor);
        editor.commit();
    }

    public static String getUltimaActualizacion(Activity act){

        return getShared(act).getString("ultimaActualizacion","2000-01-01 00:00:00");
    }

    public static long getIdUsuario(Activity act){

        return getShared(act).getLong("idUsuario",-1);
    }

    public static String getNombreUsuario(Activity act){

        return getShared(act).getString("nombreUsuario",null);
    }

    public static String getPassword(Activity act){

        return getShared(act).getString("password",null);
    }
}
