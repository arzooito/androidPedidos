package com.example.almerimatik.pedidostienda.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.almerimatik.pedidostienda.R;

import java.util.Date;

/**
 * Created by arzoo on 29/04/2018.
 */

public class DatePickerDialog extends DialogFragment {

    public static final String MINIMA = "minima";
    public static final String MAXIMA = "maxima";

    DatePicker datePicker;

    public static DatePickerDialog newInstance(long minima, long maxima) {
        DatePickerDialog fragment = new DatePickerDialog();
        Bundle args = new Bundle();
        args.putLong(MINIMA, minima);
        args.putLong(MAXIMA, maxima);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        long minima = getArguments().getLong(MINIMA);
        long maxima = getArguments().getLong(MAXIMA);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v= inflater.inflate(R.layout.dialog_date_picker, null);

        datePicker = (DatePicker) v.findViewById(R.id.datePicker);
        datePicker.setMinDate(minima);
        datePicker.setMaxDate(maxima);

        builder.setView(v);
        builder.setPositiveButton(R.string.select, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return datePicker.get
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

        return builder.create();
    }

}
