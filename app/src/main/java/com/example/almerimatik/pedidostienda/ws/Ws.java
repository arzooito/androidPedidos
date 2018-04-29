package com.example.almerimatik.pedidostienda.ws;

import android.util.Log;

import com.example.almerimatik.pedidostienda.constantes.Rutas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class Ws {

    private static final String NAMESPACE = "http://tienda_ws.almerimatik.es/";
    //localhost desde emulador
    //private static final String URL = Rutas.URL_BASE+"/tienda_WS/ServicioAppTienda?wsdl";
    //pi server
    private static final String URL = "http://gabarron.hopto.org/tienda_WS/ServicioAppTienda?wsdl";


    public static long login(String usuario, String password){

        String METHOD = "login";
        String SOAP_ACTION = NAMESPACE+METHOD;

        long result = -1;
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("nombre", usuario);
        request.addProperty("password", password);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(SOAP_ACTION, envelope);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            result = Long.valueOf(resultado_xml.toString());
            System.out.println("Servicio de logueo ejecutado con exito. Valor devuelto ==> " + result);
        }
        catch (Exception e)
        {
            Log.e("AppTienda", "Error al conectar con el servicio "+ METHOD, e);
        }

        return result;
    }

    public static long registrarUsuario(String usuario, String email, String telefono, String password){

        String METHOD = "registrarUsuario";
        String SOAP_ACTION = NAMESPACE+METHOD;

        long result = -1;
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("nombre", usuario);
        request.addProperty("password", password);
        request.addProperty("mail", email);
        request.addProperty("telefono", telefono);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(SOAP_ACTION, envelope);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            result = Long.valueOf(resultado_xml.toString());
        }
        catch (Exception e)
        {
            Log.e("AppTienda", "Error al conectar con el servicio "+ METHOD, e);
        }

        return result;
    }

    public static boolean guardarPedido(String pedidoXML){

        String METHOD = "guardarPedido";
        String SOAP_ACTION = NAMESPACE+METHOD;

        boolean result = false;
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("pedido", pedidoXML);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(SOAP_ACTION, envelope);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            result = Boolean.valueOf(resultado_xml.toString());
        }
        catch (Exception e)
        {
            Log.e("AppTienda", "Error al conectar con el servicio "+ METHOD, e);
        }

        return result;
    }

    public static String actualizar(String fecha){

        String METHOD = "actualizar";
        String SOAP_ACTION = NAMESPACE+METHOD;

        String result = null;
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty("fecha", fecha);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            transporte.call(SOAP_ACTION, envelope);
            SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
            result = resultado_xml.toString();
        }
        catch (Exception e)
        {
            Log.e("AppTienda", "Error al conectar con el servicio "+ METHOD, e);
        }

        return result;
    }
}
