package com.example.almerimatik.pedidostienda.tools;

/**
 * Created by Almerimatik on 14/02/2018.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Fechas {

    public static final SimpleDateFormat SDF_FECHA = new SimpleDateFormat("EEEE, d MMMM", new Locale("ES"));
    public static final SimpleDateFormat SDF_HORA = new SimpleDateFormat("HH:mm", new Locale("ES"));

    private final static String FORMATO_HORA = "HH:mm";
    private final static String FORMATO_FECHA_CORTO = "dd-MM-yyyy";
    private final static String FORMATO_FECHA_HORA = "dd-MM-yyyy HH:mm";
    private final static String FORMATO_FECHA_HORA_SQL = "yyyy-MM-dd HH:mm";
    private final static String FECHA_MINIMA_SQL = "01-01-1753";
    private final static String FECHA_MAXIMA_SQL = "31-12-999";

    private static Calendar HOY = Calendar.getInstance();

    public static Date Convertir(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_CORTO);
        try {
            return sdf.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date Convertir(String fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        try {
            return sdf.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date ConvertirHora(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_HORA);
        try {
            return sdf.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date Convertir(String fecha, boolean hora) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_HORA);
        try {
            return sdf.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static boolean Validar(String fecha) {
        if (fecha == null) {
            return false;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_FECHA_CORTO);
            if (fecha.trim().length() != dateFormat.toPattern().length()) {
                return false;
            } else {
                dateFormat.setLenient(false);
                try {
                    Date aux = dateFormat.parse(fecha.trim());
                    Date minima = dateFormat.parse(FECHA_MINIMA_SQL);
                    Date maxima = dateFormat.parse(FECHA_MAXIMA_SQL);
                    return aux.after(minima) && aux.after(maxima);
                } catch (ParseException pe) {
                    return false;
                }
            }
        }
    }

    public static boolean FechaHoraValida(String fecha) {
        if (fecha == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy H:mm");
        try {
            sdf.parse(fecha.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static Date Fin() {
        return Fin(HOY.getTime());
    }

    public static Date Fin(Date fecha) {
        if (fecha == null) {
            return null;
        } else {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fecha);
//            calendario.getActualMaximum(Calendar.HOUR_OF_DAY));
            calendario.set(Calendar.HOUR_OF_DAY, 23);
            calendario.set(Calendar.MINUTE, 59);
            calendario.set(Calendar.SECOND, 59);
            calendario.set(Calendar.MILLISECOND, 999);
            return calendario.getTime();
        }
    }

    public static Date Hoy() {
        return new Date();
    }

    public static Date FechaInicioMes() {
        return FechaInicioMes(HOY.getTime());
    }

    public static Date FechaInicioMes(Date fecha) {
        if(fecha == null) {
            fecha = new Date();
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.set(Calendar.DATE, 1);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return calendario.getTime();
    }

    public static Date FechaInicioEjercicio() {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.DATE, 1);
        calendario.set(Calendar.MONTH, Calendar.JANUARY);
        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return calendario.getTime();
    }

    public static Date FechaFinMes() {
        return FechaFinMes(HOY.getTime());
    }

    public static Date FechaFinMes(Date fecha) {
        if(fecha == null) {
            fecha = new Date();
        }
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.set(Calendar.DATE, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendario.set(Calendar.HOUR_OF_DAY, 23);
        calendario.set(Calendar.MINUTE, 59);
        calendario.set(Calendar.SECOND, 59);
        calendario.set(Calendar.MILLISECOND, 999);
        return calendario.getTime();
    }

    public static Date Incrementar(Date fecha, int campo, int incremento) {
        if(fecha == null) {
            fecha = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.add(campo, incremento);
        return c.getTime();
    }

    public static Date Establecer(Date fecha, int campo, int valor) {
        if(fecha == null) {
            fecha = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(campo, valor);
        return c.getTime();
    }

    public static int Obtener(Date fecha, int campo) {
        if(fecha == null) {
            fecha = new Date();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        return c.get(campo);
    }

    public static Date Medianoche() {
        return Medianoche(HOY.getTime());
    }

    public static Date Medianoche(Date fecha) {
        if(fecha == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static String FormatearFecha() {
        return FormatearFecha(new Date());
    }

    public static String FormatearFecha(Date fecha) {
        return FormatearFecha(fecha, FORMATO_FECHA_CORTO);
    }

    public static String FormatearFecha(String formato) {
        return FormatearFecha(new Date(), formato);
    }

    public static String FormatearFecha(Date fecha, String formato) {
        String resultado = "";
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            resultado = sdf.format(fecha);
        }
        return resultado;
    }

    public static String formatearFecha(final Long milis) {
        if (milis != null) {
            return SDF_FECHA.format(new Date(milis));
        } else {
            return null;
        }
    }

    public static String formatearHora(final Long milis) {
        if (milis != null) {
            return SDF_HORA.format(new Date(milis));
        } else {
            return null;
        }
    }

    public static String FormatearHora() {
        return FormatearHora(new Date());
    }

    public static String FormatearHora(Date fecha) {
        String resultado = "";
        if (fecha != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_HORA);
            resultado = sdf.format(fecha);
            return resultado;
        }
        return resultado;
    }

    public static String FormatearFechaHora() {
        return FormatearFechaHora(new Date());
    }

    public static String FormatearFechaHoraSQL() {
        return FormatearFecha(new Date(), FORMATO_FECHA_HORA_SQL);
    }

    public static String FormatearFechaHora(Date fecha) {
        String resultado;
        if (fecha == null) {
            return "(fecha vacia)";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_HORA);
            resultado = sdf.format(fecha);
            return resultado;
        }
        //DateFormat df;yyyy-mm-dd HH:mm:ss
        //df = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM);

    }

    public static Date componer(Date fecha, Date hora) {
        if(fecha == null || hora == null) {
            return null;
        }

        Calendar cFecha = Calendar.getInstance();
        Calendar cHora = Calendar.getInstance();
        cFecha.setTime(fecha);
        cHora.setTime(hora);

        cFecha.set(Calendar.HOUR_OF_DAY, cHora.get(Calendar.HOUR_OF_DAY));
        cFecha.set(Calendar.MINUTE, cHora.get(Calendar.MINUTE));
        cFecha.set(Calendar.SECOND, 0);
        cFecha.set(Calendar.MILLISECOND, 0);

        return cFecha.getTime();
    }

    public static Date componer(Integer anio, Integer mes, Integer dia) {
        Date resultado = Medianoche();
        if(anio != null) {
            resultado = Establecer(resultado, Calendar.YEAR, anio);
        }
        if(mes != null) {
            resultado = Establecer(resultado, Calendar.MONTH, mes - 1);
        }
        if(dia != null) {
            resultado = Establecer(resultado, Calendar.DATE, dia);
        }
        return resultado;
    }

    public static Integer CalcularEdad(Date fecha){
        if(fecha != null) {
//            LocalDate start = LocalDate.parse(Fechas.FormatearFecha(d1, "yyyy-MM-dd"));
//            LocalDate end = LocalDate.now();
//            return (int) ChronoUnit.YEARS.between(start, end);

            Calendar nacimiento = Calendar.getInstance();
            nacimiento.setTimeInMillis(fecha.getTime());

            int edad = HOY.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
            if(HOY.get(Calendar.DAY_OF_YEAR) < nacimiento.get(Calendar.DAY_OF_YEAR)) {
                edad--;
            }

            return edad;
        }
        else {
            return null;
        }
    }

//    public static int DiasDiferencia(Date d1, Date d2) {
//        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//    }

    public static int DiasDiferencia(Calendar c1, Calendar c2) {
        if(c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            int diferencia = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
            if(c2.get(Calendar.HOUR_OF_DAY) > c1.get(Calendar.HOUR_OF_DAY)) {
                diferencia++;
            }
            else if(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY) && c2.get(Calendar.MINUTE) >= c1.get(Calendar.MINUTE)) {
                diferencia++;
            }
            return diferencia;
        }
        else {
            int diferencia = 1;

            Calendar truncado1 = Calendar.getInstance();
            truncado1.setTimeInMillis(c1.getTimeInMillis());
            truncado1.set(Calendar.HOUR_OF_DAY, 0);
            truncado1.set(Calendar.MINUTE, 0);

            Calendar truncado2 = Calendar.getInstance();
            truncado2.setTimeInMillis(c2.getTimeInMillis());
            truncado2.set(Calendar.HOUR_OF_DAY, 0);
            truncado2.set(Calendar.MINUTE, 0);

            int horas = (int) ((truncado2.getTimeInMillis()- truncado1.getTimeInMillis()) / (1000 * 60 * 60));
            if(horas >= 24) {
                diferencia = horas / 24;
                if(c2.get(Calendar.HOUR_OF_DAY) > c1.get(Calendar.HOUR_OF_DAY)) {
                    diferencia++;
                }
                else if(c2.get(Calendar.HOUR_OF_DAY) == c1.get(Calendar.HOUR_OF_DAY) && c2.get(Calendar.MINUTE) >= c1.get(Calendar.MINUTE)) {
                    diferencia++;
                }
            }

            return diferencia;
        }
    }

//    public static int HorasDiferencia(Date d1, Date d2){
////        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60));
//        return (int)((d2.getTime() - d1.getTime()) / (1000 * 60 * 60)) % 24;
//    }

    public static String getSufijoFecha() {
        return Fechas.FormatearFecha("ddMMyyyy_HHmm_SSS");
    }

    public static boolean EstaEntre(Date fecha, Date desde, Date hasta) {
        if(fecha == null) {
            return desde == null && hasta == null;
        }
        else {
            if(desde != null && hasta != null) {
                return desde.compareTo(fecha) <= 0 && hasta.compareTo(fecha) >= 0;
            }
            else if(desde != null) {
                return desde.compareTo(fecha) <= 0;
            }
            else if(hasta != null) {
                return hasta.compareTo(fecha) >= 0;
            }
            else {
                return true;
            }
        }
    }

    public static void actualizarHoy() {
        HOY = Calendar.getInstance();
    }
}
