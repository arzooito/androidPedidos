package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.almerimatik.pedidostienda.R;

public class MainActivity extends AppCompatActivity {

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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent,LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == LOGIN) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this,"It works",Toast.LENGTH_LONG).show();
            }
        }
    }
}
