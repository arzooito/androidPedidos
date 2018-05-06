package com.example.almerimatik.pedidostienda.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.ListaActivity;
import com.example.almerimatik.pedidostienda.activity.ListasActivity;
import com.example.almerimatik.pedidostienda.asynTasks.CargarListasTask;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.entity.Lista;
import com.example.almerimatik.pedidostienda.modelo.BD;
import com.example.almerimatik.pedidostienda.tools.Msg;

/**
 * Created by arzoo on 01/05/2018.
 */

public class ListaDialog extends DialogFragment {

    EditText inputNombre;
    TextView tvTitulo, tvMensaje;
    Lista lista;

    public ListaDialog(){

    }

    @SuppressLint("ValidFragment")
    public ListaDialog(Lista lista){
        this.lista = lista;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_lista, null);
        inputNombre = (EditText) v.findViewById(R.id.nombre_input);
        tvTitulo = (TextView) v.findViewById(R.id.titulo);
        tvMensaje = (TextView) v.findViewById(R.id.mensaje);

        if(lista != null){
            tvTitulo.setText(R.string.renombrar_lista);
            tvMensaje.setText(R.string.nuevo_nombre);
        }

        builder.setPositiveButton(R.string.crear, null);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setView(v);

        final AlertDialog mDialog = builder.create();
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button pos = mDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                pos.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        if(lista != null){
                            updateLista();
                        }else{
                            crearNuevaLista();
                        }
                    }
                });
            }
        });

        return mDialog;
    }

    public void crearNuevaLista(){

        String nombre =inputNombre.getText().toString();
        if(!nombre.equals(null) && !nombre.equals("")){
            Lista lista = new Lista();
            lista.setNombre(nombre);
            lista.setProductos(Sesion.getCarrito());
            guardarLista(lista);
            dismiss();
            abrirListasActivity();
            Msg.toast(getActivity(), R.string.exito_guardar);
        }else{
            Msg.toast(getActivity(), R.string.introducir_nombre);
        }
    }

    public void updateLista(){

        String nombre =inputNombre.getText().toString();
        if(!nombre.equals(null) && !nombre.equals("")){
            lista.setNombre(nombre);
            actualizarLista(lista);
            abrirListaActivity(lista);
            dismiss();
            Msg.toast(getActivity(), R.string.exito_actualizar_lista);
        }else{
            Msg.toast(getActivity(), R.string.introducir_nombre);
        }
    }

    public void guardarLista(Lista lis){

        if(lis != null) {;

            BD bd = new BD(getActivity());
            bd.openBD(true);
            bd.guardarLista(lis);
            bd.closeBD();
        }
    }

    public void actualizarLista(Lista lis){
        BD bd = new BD(getActivity());
        bd.openBD(true);
        bd.updateLista(lis);
        bd.closeBD();
    }

    public void abrirListaActivity(Lista lis){
        Intent intent = new Intent(getActivity(), ListaActivity.class);
        intent.putExtra("objetoLista", lis);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getActivity().startActivity(intent);
    }

    public void abrirListasActivity(){
        new CargarListasTask(getActivity()).execute();
    }
}
