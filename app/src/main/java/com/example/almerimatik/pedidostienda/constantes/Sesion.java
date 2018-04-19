package com.example.almerimatik.pedidostienda.constantes;

import android.app.Activity;

import com.example.almerimatik.pedidostienda.entity.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Almerimatik on 09/02/2018.
 */

public class Sesion {

    private static long idUsuario;
    private static String nombreUsuario;
    private static String password;
    private static ArrayList<Producto> carrito;

    public static void init(Activity act){

        setIdUsuario(Data.getIdUsuario(act));
        setNombreUsuario(Data.getNombreUsuario(act));
        setPassword(Data.getPassword(act));
        carrito = new ArrayList<>();
    }

    public static long getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(long idUsuario) {
        Sesion.idUsuario = idUsuario;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        Sesion.nombreUsuario = nombreUsuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Sesion.password = password;
    }

    public static ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public static void setCarrito(ArrayList<Producto> carrito) {
        Sesion.carrito = carrito;
    }

    public static void limpiarSesion(){

        setIdUsuario(-1);
        setNombreUsuario(null);
        setPassword(null);
        carrito.clear();
    }
}
