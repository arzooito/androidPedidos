package com.example.almerimatik.pedidostienda.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.BaseActivity;
import com.example.almerimatik.pedidostienda.activity.CarritoActivity;

/**
 * Created by Almerimatik on 14/02/2018.
 */

public class Msg {

    public static void mensaje(final Context contexto, final String titulo, final String mensaje,
                               final boolean finalizar)
    {
        new AlertDialog.Builder(contexto).setMessage(mensaje).setTitle(titulo).
                setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        if (finalizar && contexto instanceof FragmentActivity) {
                            ((FragmentActivity) contexto).finish();
                        }
                    }
                }).show();
    }

    public static void toast(final Context contexto, final String mensaje) {
        final Toast t = Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG);
        t.show();
    }

    public static void toast(final Context contexto, final int mensaje) {
        final Toast t = Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG);
        t.show();
    }

    public static void salir(final Context contexto)
    {
        String mensaje = contexto.getString(R.string.salir_qu);
        String titulo = contexto.getString(R.string.salir);

        new AlertDialog.Builder(contexto).setMessage(mensaje).setTitle(titulo).
                setPositiveButton(R.string.salir, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        Activity act = (Activity) contexto;
                        act.finish();
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        }).show();
    }

    public static void preguntarLogout(final Context contexto)
    {
        String mensaje = contexto.getString(R.string.logout_qu);
        String titulo = contexto.getString(R.string.logout);

        new AlertDialog.Builder(contexto).setMessage(mensaje).setTitle(titulo).
                setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        BaseActivity act = (BaseActivity) contexto;
                        act.logout();
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        }).show();
    }

    public static void preguntarLimpiarCarrito(final Context contexto)
    {
        String mensaje = contexto.getString(R.string.vaciar_carrito_qu);
        String titulo = contexto.getString(R.string.vaciar_carrito);

        new AlertDialog.Builder(contexto).setMessage(mensaje).setTitle(titulo).
                setPositiveButton(R.string.vaciar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        CarritoActivity act = (CarritoActivity) contexto;
                        act.limpiarCarrito();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
            }
        }).show();
    }

}
