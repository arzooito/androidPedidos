package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.almerimatik.pedidostienda.R;

import static com.example.almerimatik.pedidostienda.constantes.Tipo.HISTORIAL;

/**
 * Created by Almerimatik on 18/01/2018.
 */

public class HistorialActivity extends ListadoProductos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTipo = HISTORIAL;
    }
}
