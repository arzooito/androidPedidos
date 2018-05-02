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
import com.example.almerimatik.pedidostienda.asynTasks.CargarCatalogoTask;
import com.example.almerimatik.pedidostienda.asynTasks.CargarHistorialTask;
import com.example.almerimatik.pedidostienda.asynTasks.CargarListasTask;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Msg;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.constantes.Sesion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arzoo on 25/02/2018.
 */

public class BaseActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


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
            if(this instanceof CarritoActivity){
                super.onBackPressed();
            }else if(!(this instanceof MenuPrincipalActivity)){
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
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_carrito) {
            if(!(this instanceof CarritoActivity)){
                abrirCarrito();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            if (!(this instanceof MenuPrincipalActivity)) {
                abrirMenuPrincipal();
            }
        }

        if (id == R.id.nav_catalogo) {
            if(!(this instanceof CatalogoActivity)){
                abrirCatalogo();
            }

        } else if (id == R.id.nav_lista) {
            if(!(this instanceof ListasActivity)){
                abrirListas();
            }

        } else if (id == R.id.nav_historial) {
            if(!(this instanceof HistorialActivity)){
                abrirListas();
            }

        } else if (id == R.id.nav_actualizar) {
            abrirMain();

        } else if (id == R.id.nav_logout) {
            Msg.preguntarLogout(this);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirCatalogo() {

        new CargarCatalogoTask(this).execute();
    }

    public void abrirListas() {

        new CargarListasTask(this).execute();
    }

    public void abrirHistorial() {

        new CargarHistorialTask(this).execute();
    }

    public void abrirCarrito() {

        Intent intent = new Intent(this, CarritoActivity.class);
        ArrayList<Producto> lista = Sesion.getCarrito();
        intent.putExtra("lista", (Serializable) lista);
        startActivity(intent);
    }

    public void abrirMenuPrincipal(){

        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    public void abrirMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logout(){

        Data.limpiarSesion(this);
        Sesion.limpiarSesion();
        Intent intent  = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
