package com.example.almerimatik.pedidostienda.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.constantes.Data;
import com.example.almerimatik.pedidostienda.constantes.Sesion;
import com.example.almerimatik.pedidostienda.ws.Ws;

public class MainActivity  extends FragmentActivity {

    long idUsuario;
    String nameUser = null;
    String pass = null;
    boolean remember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sesion.init(this);
        iniciar();
    }

    public void abrirMenuPrincipal() {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }


    public void abrirLogin() {
        DialogFragment newFragment = new LoginDialog();
        newFragment.show(getFragmentManager(),"LoginDialog");
    }

    public void abrirRegistrar() {
        DialogFragment newFragment = new RegistroDialog();
        newFragment.show(getFragmentManager(),"RegistroDialog");
    }


    public void iniciar(){
        idUsuario = Sesion.getIdUsuario();

        if(idUsuario < 0){
            abrirLogin();
        }else{
            abrirMenuPrincipal();
        }

    }

    public void setUsuarioPref(){
        Data.setIdUsuario(this,idUsuario);
        Data.setNombreUsuario(this,nameUser);
        Data.setPassword(this,pass);
    }

    public void setUsuarioSesion(){
        Sesion.setIdUsuario(idUsuario);
        Sesion.setNombreUsuario(nameUser);
        Sesion.setPassword(pass);
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public class LoginTask extends AsyncTask<String, Void, Void> {

        boolean autenticado = false;

        //protected void onPreExecute(){
        //progress.show();
        // }

        protected Void doInBackground(String... params) {
            login(params[0],params[1]);
            return null;
        }

        protected void onPostExecute(Void result) {
            if(autenticado){
                if(remember){
                    setUsuarioPref();
                }
                
                setUsuarioSesion();
                iniciar();
            }
        }

        private void login(String usuario, String password){
            idUsuario = Ws.login(usuario,password);
            autenticado = !(idUsuario < 0);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class RegistrarTask extends AsyncTask<String, Void, Void> {

        boolean registrado = false;

        protected Void doInBackground(String... params) {
            registrar(params[0],params[1],params[2],params[3]);
            return null;
        }

        protected void onPostExecute(Void result) {
            if(registrado){

            }
        }

        private void registrar(String usuario, String email, String telefono, String password){
            long idUsuario = Ws.registrarUsuario(usuario, email, telefono, password);
            registrado = !(idUsuario < 0);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressLint("ValidFragment")
    public class LoginDialog extends DialogFragment {

        EditText etUsuario, etPassword;
        String mensaje;
        Button btnLoguear;
        CheckBox checkRecordar;
        TextView tvRegistrar;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View v= inflater.inflate(R.layout.dialog_login, null);

            etUsuario = (EditText) v.findViewById(R.id.etUsuario);
            etPassword = (EditText) v.findViewById(R.id.etPassword);
            checkRecordar = (CheckBox) v.findViewById(R.id.recordar_check);
            btnLoguear = (Button) v.findViewById(R.id.btnLoguear);
            //tvRegistrar = (TextView) v.findViewById(R.id.tvRegistrar)

            builder.setView(v);

            btnLoguear.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nameUser = etUsuario.getText().toString().trim();
                            pass = etPassword.getText().toString().trim();
                            remember = checkRecordar.isChecked();

                            if(validar()){
                                loguear(nameUser,pass);
                                dismiss();
                            }else{
                                Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
                            }

                        }
                    }
            );


            return builder.create();
        }

        public void loguear(String usuario, String password){

            String[] params = {usuario,password};
            new LoginTask().execute(params);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressLint("ValidFragment")
    public class RegistroDialog extends DialogFragment {

        EditText etUsuario,  etPassword, etTelefono, etEmail;
        String mensaje;
        Button btnRegistrar;
        CheckBox checkRecordar;

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
            btnRegistrar = (Button) v.findViewById(R.id.btnLoguear);
            checkRecordar = (CheckBox) v.findViewById(R.id.recordar_check);

            etEmail.setVisibility(View.VISIBLE);
            etTelefono.setVisibility(View.VISIBLE);
            checkRecordar.setVisibility(View.GONE);

            btnRegistrar.setText(R.string.boton_registrar);

            builder.setView(v);

            btnRegistrar.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String user = etUsuario.getText().toString().trim();
                            String email = etEmail.getText().toString().trim();
                            String telefono = etTelefono.getText().toString().trim();
                            String password = etPassword.getText().toString().trim();

                            if(validar()){
                                registrar(user, email, telefono, password);
                            }else{
                                Toast.makeText(getActivity(),mensaje,Toast.LENGTH_LONG).show();
                            }

                            dismiss();
                        }
                    }
            );


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

}
