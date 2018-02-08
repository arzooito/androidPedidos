package com.example.almerimatik.pedidostienda.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.MainActivity;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class RegistroDialog extends DialogFragment {

    EditText etUsuario,  etPassword, etTelefono, etEmail;
    String mensaje;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v= inflater.inflate(R.layout.dialog_login, null);
        etUsuario = (EditText) v.findViewById(R.id.etUsuario);
        etEmail = (EditText) v.findViewById(R.id.etEmail);
        etTelefono = (EditText) v.findViewById(R.id.etTelefono);
        etPassword = (EditText) v.findViewById(R.id.etPassword);

        etEmail.setVisibility(View.VISIBLE);
        etTelefono.setVisibility(View.VISIBLE);

        builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.boton_enviar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String user = etUsuario.getText().toString().trim();
                        String email = etEmail.getText().toString().trim();
                        String telefono = etTelefono.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();

                        if(validar()){
                            registrar(user, email, telefono, password);
                        }else{
                            Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RegistroDialog.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }

    public void registrar(String usuario, String email, String telefono, String password){

        String[] params = {usuario,email,telefono,password};
        MainActivity activity = (MainActivity) getActivity();
        activity.new RegistrarTask().execute(params);
    }


    public void mensaje(){

        mensaje = "";

        if("".equals(etUsuario.getText().toString().trim())){
            mensaje += "* Debe introducir un nombre de usuario\n";
        }
        if(!etEmail.getText().toString().trim()
                .matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")){
            mensaje += "* Debe introducir un email válido\n";
        }
        if(!etTelefono.getText().toString().trim()
                .matches("^[0-9]{9}")){
            mensaje += "* Debe introducir un telefono válido\n";
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
