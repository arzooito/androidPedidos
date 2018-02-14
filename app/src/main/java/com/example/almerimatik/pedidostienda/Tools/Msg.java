package com.example.almerimatik.pedidostienda.Tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Almerimatik on 14/02/2018.
 */

public class Msg {

    public static void mensaje(final Context contexto, final String titulo, final String mensaje,
                               final boolean finalizar)
    {
        new AlertDialog.Builder(contexto).setMessage(mensaje).setTitle(titulo).
                setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
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
        t.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        t.show();
    }
}
