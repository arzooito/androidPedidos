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
import com.example.almerimatik.pedidostienda.dialogs.DatePickerDialog;
import com.example.almerimatik.pedidostienda.dialogs.DatePickerFragment;
import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    Date fechaRecogida;

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
        fechaRecogida = new Date();
        fechaRecogida.setTime( fechaSeleccionada.getTime() + horaSeleccionada.getTime());
        pedido.setFechaRecogida(fechaRecogida);

        return pedido;
    }

    private boolean validar(){
        return fechaSeleccionada != null && horaSeleccionada != null;
    }


    public void pedirFecha(){

        Date now =  new Date();
        Date en3Dias = Fechas.Incrementar(now, Calendar.DAY_OF_MONTH, 3);

        final DatePickerDialog dialog = DatePickerDialog.newInstance(now.getTime(),en3Dias.getTime());

        dialog.show(getFragmentManager(), "datePicker");

    }

}
