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
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.Tools.Msg;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.constantes.Tipo;

/**
 * Created by arzoo on 25/02/2018.
 */

public class BaseActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    protected int activityTipo = Tipo.BASE;
    TextView tvIdUser, tvNombreUser, tvFechaAct;

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

        tvIdUser = (TextView) navigationView.getHeaderView(0).findViewById(R.id.idUser);
        tvNombreUser = (TextView) navigationView.getHeaderView(0).findViewById(R.id.usuarioNombre);
        tvFechaAct = (TextView) navigationView.getHeaderView(0).findViewById(R.id.fechaActualizacion);

        String id = String.format("%d", Sesion.getIdUsuario());
        tvIdUser.setText(id);
        tvNombreUser.setText(Sesion.getNombreUsuario());
        tvFechaAct.setText(Data.getUltimaActualizacion(this));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(this.getActivityTipo() == Tipo.LISTA){
                super.onBackPressed();
            }else if(this.getActivityTipo() != Tipo.PRINCIPAL){
                Intent intent  = new Intent(this,MenuPrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else{
                Msg.salir(this);
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

        int id = item.getItemId();
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
            Data.limpiarSesion(this);
            Sesion.limpiarSesion();
            intent  = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
