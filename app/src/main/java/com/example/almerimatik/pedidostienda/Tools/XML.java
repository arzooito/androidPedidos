package com.example.almerimatik.pedidostienda.Tools;

import android.util.Log;

import com.example.almerimatik.pedidostienda.Modelo.BD;
import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;

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

    public static List<Producto> getProductosEntran(Element entran, BD bd) throws DOMException,NumberFormatException{

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
                    String idMarca = elementoProducto.getAttribute("idMarca");
                    String formato = elementoProducto.getAttribute("formato");
                    String precio = elementoProducto.getAttribute("precio");
                    String foto = elementoProducto.getAttribute("foto");
                    String idSub = elementoProducto.getAttribute("idSubcategoria");

                    long id = Long.valueOf(s_id);
                    bd.openBD(false);
                    Marca marca = bd.getMarca(idMarca);
                    Subcategoria sub = bd.getSubcategoria(idSub);
                    bd.closeBD();

                    prod.setId(id);
                    prod.setNombre(nombre);
                    prod.setMarca(marca);
                    prod.setFormato(formato);
                    prod.setPrecio(Float.parseFloat(precio));
                    prod.setFoto(foto);
                    prod.setSubcategoria(sub);

                    productos.add(prod);
                }
            }
        }

        return productos;
    }

    public static List<Marca> getMarcasEntran(Element elementoMarcas) throws DOMException,NumberFormatException{

        List<Marca> marcas = new ArrayList<>();

        if(elementoMarcas != null){
            int i;
            Marca marca;

            NodeList nodosMarca = elementoMarcas.getChildNodes();
            Element elementoMarca = null;

            if(nodosMarca != null && nodosMarca.getLength()>0){
                for(i=0; i<nodosMarca.getLength();i++){

                    marca = new Marca();
                    elementoMarca = (Element)nodosMarca.item(i);

                    String s_id = elementoMarca.getAttribute("id");
                    String nombre = elementoMarca.getAttribute("nombre");


                    long id = Long.valueOf(s_id);

                    marca.setId(id);
                    marca.setNombre(nombre);

                    marcas.add(marca);
                }
            }
        }

        return marcas;
    }

    public static List<Categoria> getCategoriasEntran(Element elementoCategorias) throws DOMException,NumberFormatException{

        List<Categoria> categorias = new ArrayList<>();

        if(elementoCategorias != null){
            int i;
            Categoria cat;

            NodeList nodosCategoria = elementoCategorias.getChildNodes();
            Element elementoCategoria = null;

            if(nodosCategoria != null && nodosCategoria.getLength()>0){
                for(i=0; i<nodosCategoria.getLength();i++){

                    cat = new Categoria();
                    elementoCategoria = (Element)nodosCategoria.item(i);

                    String s_id = elementoCategoria.getAttribute("id");
                    String nombre = elementoCategoria.getAttribute("nombre");


                    long id = Long.valueOf(s_id);

                    cat.setId(id);
                    cat.setNombre(nombre);

                    categorias.add(cat);
                }
            }
        }

        return categorias;
    }

    public static List<Subcategoria> getSubcategoriasEntran(Element elementoSubcategorias, BD bd) throws DOMException,NumberFormatException{

        List<Subcategoria> subcategorias = new ArrayList<>();

        if(elementoSubcategorias != null){
            int i;
            Subcategoria sub;

            NodeList nodosSub = elementoSubcategorias.getChildNodes();
            Element elementoSub = null;

            if(nodosSub != null && nodosSub.getLength()>0){
                for(i=0; i<nodosSub.getLength();i++){

                    sub = new Subcategoria();
                    elementoSub = (Element)nodosSub.item(i);

                    String s_id = elementoSub.getAttribute("id");
                    String nombre = elementoSub.getAttribute("nombre");
                    String idCat = elementoSub.getAttribute("idCategoria");


                    long id = Long.valueOf(s_id);
                    bd.openBD(false);
                    Categoria cat = bd.getCategoria(idCat);
                    bd.closeBD();

                    sub.setId(id);
                    sub.setNombre(nombre);
                    sub.setCategoria(cat);

                    subcategorias.add(sub);
                }
            }
        }

        return subcategorias;
    }

    public static long[] productosSalen(Element salen){

        long[] ids = null;

        if(salen != null){
            int i;
            long id;

            NodeList nodosProd = salen.getChildNodes();
            Element elementoProd = null;
            ids = new long[nodosProd.getLength()];

            if(nodosProd != null && nodosProd.getLength()>0){
                for(i=0; i<nodosProd.getLength();i++){

                    elementoProd = (Element)nodosProd.item(i);

                    String s_id = elementoProd.getAttribute("id");

                    id = Long.valueOf(s_id);

                    ids[i] = id;
                }
            }
        }

        return ids;
    }
}
