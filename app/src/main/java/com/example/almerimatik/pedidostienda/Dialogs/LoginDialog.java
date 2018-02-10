package com.example.almerimatik.pedidostienda.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.AsynTasks.LoginTask;
import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.activity.MainActivity;

/**
 * Created by Almerimatik on 07/02/2018.
 */

public class LoginDialog extends DialogFragment {

    EditText etUsuario,  etPassword;
    String mensaje;
    Button btnLoguear, btnRegistrar;
    CheckBox checkRecordar;
    TextView tvRegistrar;
    MainActivity activity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        activity = (MainActivity) getActivity();
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v= inflater.inflate(R.layout.dialog_login, null);

        etUsuario = (EditText) v.findViewById(R.id.etUsuario);
        etPassword = (EditText) v.findViewById(R.id.etPassword);
        checkRecordar = (CheckBox) v.findViewById(R.id.recordar_check);
        btnLoguear = (Button) v.findViewById(R.id.btnLoguear);
        tvRegistrar = (TextView) v.findViewById(R.id.tvRegistrar);

        builder.setView(v);

        btnLoguear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = etUsuario.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();
                        boolean remember = checkRecordar.isChecked();
                        activity.setNameUser(user);
                        activity.setPass(password);
                        activity.setRemember(remember);

                        if(validar()){
                            loguear(user,password);
                        }else{
                            Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        tvRegistrar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.abrirRegistrar();
                    }
                }
        );


        return builder.create();
    }

    public void loguear(String usuario, String password){

        String[] params = {usuario,password};
        new LoginTask(activity).execute(params);
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

    public void clearInputs(){
        etUsuario.setText("");
        etPassword.setText("");
    }

}
