package com.example.almerimatik.pedidostienda.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText etFecha, etHora;
    private Date fechaSeleccionada;

    public static final String MINIMA = "minima";
    public static final String MAXIMA = "maxima";


    public static DatePickerFragment newInstance(long minima, long maxima) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putLong(MINIMA, minima);
        args.putLong(MAXIMA, maxima);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        long minima = getArguments().getLong(MINIMA);
        long maxima = getArguments().getLong(MAXIMA);
/*
        Locale locale = new Locale("ES");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getApplicationContext().getResources().updateConfiguration(config, null);
*/

        final Calendar calendario = Calendar.getInstance();
        try {
            long timeMilis = Fechas.SDF_FECHA.parse(etFecha.getText().toString()).getTime();
            calendario.setTimeInMillis(timeMilis);
        } catch (final Exception ex){
            calendario.setTimeInMillis(new Date().getTime());
        }

        final DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));

        dpd.getDatePicker().setMinDate(minima);
        dpd.getDatePicker().setMaxDate(maxima);

        return dpd;
    }


    @Override
    public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);

        etFecha.setText(Fechas.SDF_FECHA.format(c.getTime()));
        fechaSeleccionada.setTime(c.getTimeInMillis());
        etHora.setText(null);
    }

    public void setEditTextFecha(final EditText etFecha) {
        this.etFecha = etFecha;
    }

    public void setEditTextHora(final EditText etHora) {
        this.etHora = etHora;
    }

    public void setFechaSeleccionada(Date date){
        this.fechaSeleccionada = date;
    }

}