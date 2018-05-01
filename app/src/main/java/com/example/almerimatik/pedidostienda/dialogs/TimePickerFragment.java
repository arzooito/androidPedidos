package com.example.almerimatik.pedidostienda.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.almerimatik.pedidostienda.tools.Fechas;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener  {

    private EditText etHora;
    private Date horaSeleccionada;



    public static final String DIA = "dia_semana";


    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putLong(DIA, 0);
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        final Calendar calendario = Calendar.getInstance();
        try {
            calendario.setTimeInMillis(Fechas.SDF_HORA.parse(etHora.getText().toString()).getTime());
        } catch (final Exception ex){
            calendario.setTimeInMillis(new Date().getTime());
        }

        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, calendario.get(Calendar.HOUR_OF_DAY),
                calendario.get(Calendar.MINUTE), true);

        return tpd;
   }

   @Override
   public void onTimeSet(final TimePicker view, final int hora, final int minuto) {
      final Calendar c = Calendar.getInstance();
      c.set(Calendar.HOUR_OF_DAY, hora);
      c.set(Calendar.MINUTE, minuto);
      etHora.setText(Fechas.SDF_HORA.format(c.getTime()));
      horaSeleccionada.setTime(c.getTimeInMillis());
   }

   public void setEditText(final EditText etHora){
      this.etHora = etHora;
   }

   public void setHoraSeleccionada(Date date){
       this.horaSeleccionada = date;
   }
}
