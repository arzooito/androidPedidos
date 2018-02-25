package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Tipo;

/**
 * Created by Almerimatik on 18/01/2018.
 */

public class ListadoBaseActivity <E, A extends ArrayAdapter<E>>extends BaseActivity{

    private ListView lvLista;
    protected int activityTipo = Tipo.BASE;


}
