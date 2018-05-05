package com.example.almerimatik.pedidostienda.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.ListadoProductoActivity;
import com.example.almerimatik.pedidostienda.adaptadores.CarritoAdapter;
import com.example.almerimatik.pedidostienda.entity.Producto;

import java.io.Serializable;

/**
 * Created by arzoo on 05/05/2018.
 */

public class EditarCantidadDialog extends DialogFragment {

    TextView tvCantidad;
    ImageButton btnSumar, btnRestar;
    int cantidad = 0;
    Producto producto;
    ListadoProductoActivity act;



    public static EditarCantidadDialog newInstance(Context context, Producto prod) {
        EditarCantidadDialog dialog = new EditarCantidadDialog();
        Bundle args = new Bundle();
        args.putSerializable("producto", prod);
        dialog.setContext(context);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_editar_producto, null);
        tvCantidad = (TextView) v.findViewById(R.id.tvCantidadItemProd);
        btnSumar = (ImageButton) v.findViewById(R.id.btnAddCantidad);
        btnRestar = (ImageButton) v.findViewById(R.id.btnRmCantidad);
        inicializarProducto();

        builder.setView(v);

        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sumar();
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restar();
            }
        });

        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                act.editarProducto(cantidad);
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void inicializarProducto(){

        producto = (Producto) getArguments().getSerializable("producto");

        if(producto != null){
            cantidad = producto.getCantidad();
            tvCantidad.setText(String.valueOf(cantidad));
        }
    }

    private void sumar(){

        cantidad ++;
        tvCantidad.setText(String.valueOf(cantidad));
    }

    private void restar(){
        if(cantidad >1){
            cantidad --;
            tvCantidad.setText(String.valueOf(cantidad));
        }
    }

    private void setContext(Context context){

        this.act = (ListadoProductoActivity)context;
    }


}
