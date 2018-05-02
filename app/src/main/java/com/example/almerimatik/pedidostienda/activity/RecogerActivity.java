package com.example.almerimatik.pedidostienda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.tools.Fechas;

/**
 * Created by arzoo on 01/05/2018.
 */

public class RecogerActivity extends AppCompatActivity {

    TextView tvFecha, tvHora;
    Button btnVolver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogida);

        tvFecha = (TextView) findViewById(R.id.fecha);
        tvHora = (TextView) findViewById(R.id.hora);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });

        Pedido pedido = (Pedido) getIntent().getSerializableExtra("pedido");
        String sFecha = Fechas.FormatearFecha(pedido.getFechaRecogida(), Fechas.FORMATO_FECHA_CERCANA);
        String sHora = Fechas.FormatearHora(pedido.getHoraRecogida());

        tvFecha.setText(sFecha);
        tvHora.setText(sHora);
    }

    @Override
    public void onBackPressed() {
        volver();
    }

    public void volver(){
        Intent intent = new Intent(this, MenuPrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
