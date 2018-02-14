package com.example.almerimatik.pedidostienda.Tools;

import android.util.Log;

import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by arzoo on 12/02/2018.
 */

public class XML {

    public static Document getDocumento(String xml) {
        if (!isBlank(xml)) {
            try {
                DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                docBuilder.setErrorHandler(new ErrorHandler() {
                    @Override
                    public void warning(SAXParseException exception) throws SAXException {
                    }

                    @Override
                    public void error(SAXParseException exception) throws SAXException {
                    }

                    @Override
                    public void fatalError(SAXParseException exception) throws SAXException {
                    }
                });

                return docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            }
            catch(Exception ex) {
                Log.e("policia", "", ex);
            }
        }
        return null;
    }

    public static boolean isBlank(final String s) {
        return s == null || s.trim().length() == 0;
    }
/*
    public static List<Producto> getProductosEntran(Element entran) throws DOMException,NumberFormatException{

        List<Producto> productos = new ArrayList<>();

        if(entran != null){
            int i;
            Producto prod;

            NodeList nodosProducto = entran.getChildNodes();
            Element elementoProducto = null;

            if(nodosProducto != null && nodosProducto.getLength()>0){
                for(i=0; i<nodosProducto.getLength();i++){

                    prod = new Producto();
                    elementoProducto = (Element)nodosProducto.item(i);

                    String s_id = elementoProducto.getAttribute("id");
                    String nombre = elementoProducto.getAttribute("nombre");
                    String sMarca = elementoProducto.getAttribute("idMarca");
                    String formato = elementoProducto.getAttribute("formato");
                    String precio = elementoProducto.getAttribute("precio");
                    String foto = elementoProducto.getAttribute("foto");
                    String sub = elementoProducto.getAttribute("idSubcategoria");

                    long id = Long.valueOf(s_id);
                    long idMarca = Long.valueOf(sMarca);
                    Marca marca = DB.getMarca(idMarca);

                    prod.setId(id);
                    prod.setNombre(nombre);
                    prod.setMarca(marca);
                    prod.setFormato(formato);
                    prod.setPrecio(Float.parseFloat(precio));
                    prod.setFoto(foto);
                    prod.setCat(cat);
                    prod.setSub(sub);
                    prod.setUnds(Integer.parseInt(unds));

                    productos.add(prod);
                }
            }
        }

        return productos;
    }
    */
}
