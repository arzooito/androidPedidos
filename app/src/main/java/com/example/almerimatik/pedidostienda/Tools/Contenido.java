package com.example.almerimatik.pedidostienda.Tools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.example.almerimatik.pedidostienda.R;

/**
 * Created by arzoo on 25/02/2018.
 */

public class Contenido {

    public static void addContent(Activity act, int layout){

        View.inflate(act, layout, (ViewGroup) act.findViewById(R.id.contenedor));
    }
}
