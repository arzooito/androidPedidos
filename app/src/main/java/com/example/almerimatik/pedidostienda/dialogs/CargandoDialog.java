package com.example.almerimatik.pedidostienda.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;

/**
 * Created by arzoo on 01/05/2018.
 */

public class CargandoDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_enviando, null);
        TextView tv = (TextView) v.findViewById(R.id.tvProgress);
        tv.setText(R.string.cargando_contenido);
        builder.setView(v);
        return builder.create();
    }
}