package com.example.almerimatik.pedidostienda.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.Dialogs.LoginDialog;
import com.example.almerimatik.pedidostienda.R;

public class MainActivity  extends FragmentActivity {

    final int LOGIN = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intent);
    }

    public void abrirLogin(View view) {
        DialogFragment newFragment = new LoginDialog();
        newFragment.show(getFragmentManager(),"LoginDialog1");

    }


}
