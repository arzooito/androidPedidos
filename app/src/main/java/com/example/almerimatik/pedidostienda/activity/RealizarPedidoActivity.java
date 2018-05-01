package com.example.almerimatik.pedidostienda.activity;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.almerimatik.pedidostienda.R;
import com.example.almerimatik.pedidostienda.adaptadores.ResumenAdapter;
import com.example.almerimatik.pedidostienda.asynTasks.TramitarPedidoTask;
import com.example.almerimatik.pedidostienda.dialogs.DatePickerFragment;
import com.example.almerimatik.pedidostienda.dialogs.TimePickerFragment;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Fechas;
import com.example.almerimatik.pedidostienda.tools.Msg;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by arzoo on 22/04/2018.
 */

public class RealizarPedidoActivity extends AppCompatActivity {

    Button botonPedir;
    ListView lvLista;
    ResumenAdapter adapter;
    TextView tvTotal;
    float importeTotal;
    EditText etFecha, etHora;
    ArrayList<Producto> listaProductos;

    Date fechaSeleccionada;
    Date horaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedido);
        adapter = new ResumenAdapter(this);
        lvLista = (ListView) findViewById(R.id.lvLista);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        botonPedir = (Button) findViewById(R.id.btn_confirmar_pedido);
        etFecha = (EditText) findViewById(R.id.etFecha);
        etHora = (EditText) findViewById(R.id.etHora);

        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pedirFecha();
            }
        });

        etHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pedirHora();
            }
        });



        botonPedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarPedido();
            }
        });

        lvLista.setAdapter(adapter);
        listaProductos = (ArrayList<Producto>) getIntent().getSerializableExtra("lista");
        importeTotal = getIntent().getFloatExtra("importeTotal", 0f);
        tvTotal.setText(String.format("Total: %.2f €", importeTotal));

        rellenarLista(listaProductos);
    }

    public void realizarPedido(){

        if(validar()){
            Pedido pedido = crearPedido();
            new TramitarPedidoTask(this).execute(pedido);

        }else{
            Msg.mensaje(this, R.string.error_validacion, R.string.error_validar, false);
        }
    }

    protected void rellenarLista(final ArrayList<Producto> lista) {
        if (lista != null && !lista.isEmpty()) {
            try {
                final ResumenAdapter adapter = (ResumenAdapter) lvLista.getAdapter();
                adapter.clear();
                for (final Producto reg : lista) {
                    adapter.add(reg);
                }
            } catch (final Exception ex) {
                Log.e("ListadoResumen", "Error al rellenar lista", ex);
            }
        }
    }

    private Pedido crearPedido(){

        Pedido pedido = new Pedido();
        pedido.setFecha(new Date());
        pedido.setProductos(listaProductos);
        pedido.setFechaRecogida(fechaSeleccionada);
        pedido.setHoraRecogida(horaSeleccionada);

        return pedido;
    }

    private boolean validar(){
        return !etFecha.getText().equals(null) && !etHora.getText().equals(null);
    }


    public void pedirFecha(){

        Date now =  new Date();
        Date en3Dias = Fechas.Incrementar(now, Calendar.DAY_OF_MONTH, 3);

        final DatePickerFragment newFragment = DatePickerFragment.newInstance(now.getTime(), en3Dias.getTime());
        newFragment.setEditTextFecha(etFecha);
        newFragment.setEditTextHora(etHora);
        fechaSeleccionada = new Date();
        newFragment.setFechaSeleccionada(fechaSeleccionada);

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void pedirHora(){

        final TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setEditText(etHora);
        horaSeleccionada = new Date();
        newFragment.setHoraSeleccionada(horaSeleccionada);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}
