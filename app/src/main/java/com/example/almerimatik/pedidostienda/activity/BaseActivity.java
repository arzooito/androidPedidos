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

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Tipo;

/**
 * Created by arzoo on 25/02/2018.
 */

public class BaseActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    protected int activityTipo = Tipo.BASE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(this.getActivityTipo() == Tipo.LISTA){
                super.onBackPressed();
            }else{
                Intent intent  = new Intent(this,MenuPrincipalActivity.class);
                startActivity(intent);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.catalogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getActivityTipo(){
        return this.activityTipo;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent;

        if (id == R.id.nav_catalogo) {

            if(this.getActivityTipo() != Tipo.CATALOGO){
                intent  = new Intent(this,CatalogoActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_lista) {
            if(this.getActivityTipo() != Tipo.LISTAS){
                intent  = new Intent(this,ListasActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_historial) {
            if(this.getActivityTipo() != Tipo.HISTORIAL){
                intent  = new Intent(this,HistorialActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            //limpiarSesion();
            intent  = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
