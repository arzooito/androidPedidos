package com.example.almerimatik.pedidostienda.tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.example.almerimatik.pedidostienda.R;

/**
 * Created by arzoo on 25/02/2018.
 */

public class Contenido {

    public static ViewGroup addContent(Activity act, int layout){

        ViewGroup contenedor = (ViewGroup) act.findViewById(R.id.contenedor);
        View.inflate(act, layout, contenedor);
        return contenedor;
    }
}
