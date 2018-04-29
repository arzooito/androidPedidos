package com.example.almerimatik.pedidostienda.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.almerimatik.pedidostienda.tools.Fechas;
import com.example.almerimatik.pedidostienda.tools.Msg;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText etFecha;
    private Date minimo, maximo;

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final Calendar calendario = Calendar.getInstance();
        try {
            calendario.setTimeInMillis(Fechas.SDF_FECHA.parse(etFecha.getText().toString()).getTime());
        } catch (final Exception ex){
            calendario.setTimeInMillis(new Date().getTime());
        }

        final DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH));


        dpd.setButton(DatePickerDialog.BUTTON_NEUTRAL, "Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etFecha.setText(null);
                dialog.dismiss();
            }
        });


//        if(minimo != null) {
//            dp.setMinDate(minimo.getTime());
//        }

//        if(maximo != null) {
//            dp.setMaxDate(maximo.getTime());
//        }

//        dpd.getButton(DatePickerDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar c = Calendar.getInstance();
//                c.set(Calendar.DATE, 1);
//                c.set(Calendar.YEAR, dpd.getDatePicker().getYear());
//                c.set(Calendar.MONTH, dpd.getDatePicker().getMonth());
//                c.set(Calendar.DATE, dpd.getDatePicker().getDayOfMonth());
//
////                if((minimo != null && minimo.after(c.getTime())) || (maximo != null && maximo.before(c.getTime())))
//
//                if(minimo != null && minimo.after(c.getTime())) {
//                    Utilidades.mensaje(getActivity(), "Error", "La fecha no puede ser menor que "
//                            + Utilidades.formatearFecha(minimo.getTime()), false);
//                    return;
//                }
//
//                if(maximo != null && maximo.before(c.getTime())) {
//                    Utilidades.mensaje(getActivity(), "Error", "La fecha no puede ser menor que "
//                            + Utilidades.formatearFecha(maximo.getTime()), false);
//                    return;
//                }
//            }
//        });

        return dpd;
    }

    public void setLimites(Date minimo, Date maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }

    @Override
    public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DATE, day);

        if(minimo != null && minimo.after(c.getTime())) {

            String sMinimo = Fechas.formatearFecha(minimo.getTime());
            Msg.toast(getActivity() , "La fecha no puede ser menor que "+ sMinimo);
            return;
        }

        if(maximo != null && maximo.before(c.getTime())) {

            String sMaximo = Fechas.formatearFecha(maximo.getTime());
            Msg.toast(getActivity(), "La fecha no puede ser menor que " + sMaximo);
            return;
        }

        etFecha.setText(Fechas.SDF_FECHA.format(c.getTime()));
    }

    public void setEditText(final EditText etFecha) {
        this.etFecha = etFecha;
    }

}
