package com.example.almerimatik.pedidostienda.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;

/**
 * Created by Almerimatik on 07/02/2018.
 */

public class LoginDialog extends DialogFragment {

    EditText etUsuario,  etPassword;
    String mensaje;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v= inflater.inflate(R.layout.dialog_login, null);
        etUsuario = (EditText) v.findViewById(R.id.etUsuario);
        etPassword = (EditText) v.findViewById(R.id.etPassword);

        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.boton_login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(!validar()){
                            Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginDialog.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }


    public void mensaje(){

        mensaje = "";

        if("".equals(etUsuario.getText().toString().trim())){
            mensaje += "* Debe introducir un nombre de usuario\n";
        }
        if("".equals(etPassword.getText().toString().trim())){
            mensaje += "* Debe introducir un password\n";
        }
    }

    public boolean validar(){
        boolean valido = true;
        mensaje();
        if(!mensaje.equals("")){
            valido = false;

        }
        return valido;
    }
}
