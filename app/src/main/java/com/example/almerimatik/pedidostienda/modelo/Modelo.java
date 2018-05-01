package com.example.almerimatik.pedidostienda.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.almerimatik.pedidostienda.entity.Pedido;
import com.example.almerimatik.pedidostienda.tools.Fechas;
import com.example.almerimatik.pedidostienda.tools.Msg;
import com.example.almerimatik.pedidostienda.entity.Categoria;
import com.example.almerimatik.pedidostienda.entity.Marca;
import com.example.almerimatik.pedidostienda.entity.Producto;
import com.example.almerimatik.pedidostienda.entity.Subcategoria;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Almerimatik on 08/02/2018.
 */

public class Modelo {

    private static final String PRODUCTO = "producto";
    private static final String MARCA = "marca";
    private static final String CAT = "categoria";
    private static final String SUB = "subcategoria";
    private static final String LISTA = "lista";
    private static final String LIS_PROD = "lista_producto";
    private static final String PEDIDO = "pedido";
    private static final String PED_PROD = "pedido_producto";


    ///// MARCA ////////////////////

    public static void guardarMarca(Context context, SQLiteDatabase db, Marca marca){

        String tabla = MARCA;

        try {
            final ContentValues valores = marca.rellenar();
            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("policia", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static Marca cargarMarca(SQLiteDatabase db, Context context,String id){

        Marca marca = new Marca();

        String tabla = MARCA;
        String[] campos = marca.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                marca.setId(c.getLong(0));
                marca.setNombre(c.getString(1));
            }


        }
        c.close();
        return marca;
    }

    public static void eliminarMarca(Context context, SQLiteDatabase db, String id){

        String tabla = MARCA;
        try {
            String[] args = new String[]{id};
            String where = "id = ?";
            db.delete(tabla,where,args);
        } catch (final Exception e) {
            Log.d("tienda", "Error al eliminar de BD: ", e);
            Msg.mensaje(context, "Error", "Error al eliminar de BD: " + e.getMessage(), false);
        }
    }

    public static int[] cargarMarcasSinProducto(SQLiteDatabase db, Context context){

        int[] result = null;
        String[] args = new String[] {"0"};
        Cursor c = null;
        String query = "select m.id from marca m left join producto p on m.id = p.idMarca group by m.id having count(p.id) = ?";

        try {
            c = db.rawQuery(query, args);

        }catch (final Exception e) {
            Log.d("tienda", "Error al borrar marcas obsoletas  BD: ", e);
            Msg.mensaje(context, "Error", "Error al borrar registros obsoletos desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            int regs = c.getCount();
            result = new int[regs];

            for(int i=0; i<regs; i++, c.moveToNext()){
                int id = c.getInt(0);
                result[i] = id;
            }
        }

        c.close();
        return result;
    }

    ///////// CATEGORIA ////////

    public static void guardarCategoria(Context context, SQLiteDatabase db, Categoria cat){

        String tabla = CAT;

        try {
            final ContentValues valores = cat.rellenar();
            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static Categoria cargarCategoria(SQLiteDatabase db, Context context, String id){

        Categoria cat = new Categoria();

        String tabla = CAT;
        String[] campos = cat.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                cat.setId(c.getLong(0));
                cat.setNombre(c.getString(1));
            }
        }

        c.close();
        return cat;
    }

    public static void eliminarCategoria(Context context, SQLiteDatabase db, String id){

        String tabla = CAT;
        try {
            String[] args = new String[]{id};
            String where = "id = ?";
            db.delete(tabla,where,args);
        } catch (final Exception e) {
            Log.d("tienda", "Error al eliminar de BD: ", e);
            Msg.mensaje(context, "Error", "Error al eliminar de BD: " + e.getMessage(), false);
        }
    }

    public static int[] cargarCategoriasSinSubcategoria(SQLiteDatabase db, Context context){

        int[] result = null;
        String[] args = new String[] {"0"};
        Cursor c = null;
        String query = "select cat.id from categoria cat left join subcategoria sub on cat.id = sub.idCategoria group by cat.id having count(sub.id) = ?";

        try {
            c = db.rawQuery(query, args);

        }catch (final Exception e) {
            Log.d("tienda", "Error al borrar categorias obsoletas  BD: ", e);
            Msg.mensaje(context, "Error", "Error al borrar registros obsoletos desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            int regs = c.getCount();
            result = new int[regs];

            for(int i=0; i<regs; i++, c.moveToNext()){
                int id = c.getInt(0);
                result[i] = id;
            }
        }

        c.close();
        return result;
    }

    /////// Subcategoria ////////////////////

    public static void guardarSubcategoria(Context context, SQLiteDatabase db, Subcategoria sub){

        String tabla = SUB;

        try {
            final ContentValues valores = sub.rellenar();
            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }


    public static Subcategoria cargarSubcategoria(SQLiteDatabase db, Context context, String id){

        Subcategoria sub = new Subcategoria();

        String tabla = SUB;
        String[] campos = sub.getCampos();
        int camposCount = campos.length;
        String where = "id=?";
        String[] args = {id};
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            for(int i=0; i<camposCount; i++){

                sub.setId(c.getLong(0));
                sub.setNombre(c.getString(1));

                String idCat = c.getString(2);
                Categoria cat = Modelo.cargarCategoria(db, context, idCat);
                sub.setCategoria(cat);
            }
        }

        c.close();
        return sub;
    }

    public static void eliminarSubcategoria(Context context, SQLiteDatabase db, String id){

        String tabla = SUB;
        try {
            String[] args = new String[]{id};
            String where = "id = ?";
            db.delete(tabla,where,args);
        } catch (final Exception e) {
            Log.d("tienda", "Error al eliminar de BD: ", e);
            Msg.mensaje(context, "Error", "Error al eliminar de BD: " + e.getMessage(), false);
        }
    }

    public static int[] cargarSubcategoriasSinProducto(SQLiteDatabase db, Context context){

        int[] result = null;
        String[] args = new String[] {"0"};
        Cursor c = null;
        String query = "select s.id from subcategoria s left join producto p on s.id = p.idSubcategoria group by s.id having count(p.id) = ?";

        try {
            c = db.rawQuery(query, args);

        }catch (final Exception e) {
            Log.d("tienda", "Error al borrar subcategorias obsoletas  BD: ", e);
            Msg.mensaje(context, "Error", "Error al borrar registros obsoletos desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){

            int regs = c.getCount();
            result = new int[regs];

            for(int i=0; i<regs; i++, c.moveToNext()){
                int id = c.getInt(0);
                result[i] = id;
            }
        }

        c.close();
        return result;
    }

    //////////////////// Productos /////////////////////////////////

    public static ArrayList<Producto> cargarProductos(SQLiteDatabase db, Context context){

        String tabla = PRODUCTO;
        Producto prod = new Producto();
        ArrayList<Producto> prodList = new ArrayList<>();
        String[] campos = prod.getCampos();
        int camposCount = campos.length;
        Cursor c = null;

        try {
            c = db.query(tabla, campos, null, null, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){
            do{
                prod = new Producto();
                for(int i=0; i<camposCount; i++){

                    Marca marca;
                    Subcategoria sub;

                    prod.setId(c.getLong(0));
                    prod.setNombre(c.getString(1));
                    prod.setFormato(c.getString(2));
                    prod.setPrecio(c.getFloat(3));
                    prod.setFoto(c.getString(4));
                    marca = cargarMarca(db,context,String.valueOf(c.getLong(5)));
                    prod.setMarca(marca);
                    sub = cargarSubcategoria(db,context,String.valueOf(c.getLong(6)));
                    prod.setSubcategoria(sub);
                }
                prodList.add(prod);
            }while(c.moveToNext());
        }
        c.close();
        return prodList;
    }

    public static Producto cargarProducto(SQLiteDatabase db, Context context, long idProducto){

        String tabla = PRODUCTO;
        Producto prod = new Producto();
        ArrayList<Producto> prodList = new ArrayList<>();
        String[] campos = prod.getCampos();
        String where = "id = ?";
        String[] args = {String.valueOf(idProducto)};
        int camposCount = campos.length;
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){
                prod = new Producto();
                for(int i=0; i<camposCount; i++){

                    Marca marca;
                    Subcategoria sub;

                    prod.setId(c.getLong(0));
                    prod.setNombre(c.getString(1));
                    prod.setFormato(c.getString(2));
                    prod.setPrecio(c.getFloat(3));
                    prod.setFoto(c.getString(4));
                    marca = cargarMarca(db,context,String.valueOf(c.getLong(5)));
                    prod.setMarca(marca);
                    sub = cargarSubcategoria(db,context,String.valueOf(c.getLong(6)));
                    prod.setSubcategoria(sub);
                }
        }
        c.close();
        return prod;
    }

    public static void guardarProducto(Context context, SQLiteDatabase db, Producto prod){

        String tabla = PRODUCTO;

        try {
            final ContentValues valores = prod.rellenar();
            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static void eliminarProducto(Context context, SQLiteDatabase db, String id){

        String tabla = PRODUCTO;
        try {
            String[] args = new String[]{id};
            String where = "id = ?";
            db.delete(tabla,where,args);
        } catch (final Exception e) {
            Log.d("tienda", "Error al eliminar de BD: ", e);
            Msg.mensaje(context, "Error", "Error al eliminar de BD: " + e.getMessage(), false);
        }
    }

    //////////////////// Pedidos /////////////////////////////////

    public static void guardarPedido(Context context, SQLiteDatabase db, Pedido ped){

        String tabla = PEDIDO;

        try {
            final ContentValues valores = ped.rellenar();
            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static void guardarProductosPedido(Context context, SQLiteDatabase db, Pedido ped, Producto prod){

        String tabla = PED_PROD;

        try {
            final ContentValues valores = new ContentValues();
            valores.put("idPedido", ped.getId());
            valores.put("idProducto", prod.getId());
            valores.put("unidades", prod.getCantidad());

            db.insertWithOnConflict(tabla, null, valores, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (final Exception e) {
            Log.d("tienda", "Error al guardar en BD: ", e);
            Msg.mensaje(context, "Error", "Error al guardar en BD: " + e.getMessage(), false);
        }
    }

    public static ArrayList<Producto> cargarProductosPedido(Context context, SQLiteDatabase db, Pedido ped){

        String tabla = PED_PROD;
        Producto prod = new Producto();
        ArrayList<Producto> prodList = new ArrayList<>();
        String[] campos = {"idPedido", "idProducto", "unidades"};
        String where = "idPedido = ?";
        String[] args = {String.valueOf(ped.getId())};
        int camposCount = campos.length;
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }


        if(c != null && c.moveToFirst()){
            do{
                for(int i=0; i<camposCount; i++){

                    long idProd = c.getLong(1);
                    prod = cargarProducto(db,context, idProd);
                    prod.setCantidad(c.getInt(2));
                }
                prodList.add(prod);
            }while(c.moveToNext());
        }
        c.close();
        return prodList;
    }

    public static ArrayList<Pedido> cargarPedidos(SQLiteDatabase db, Context context){

        String tabla = PEDIDO;
        Pedido pedido = new Pedido();
        ArrayList<Pedido> pedidoList = new ArrayList<>();
        ArrayList<Producto> prodList = new ArrayList<>();
        String[] campos = pedido.getCampos();
        int camposCount = campos.length;
        Cursor c = null;

        try {
            c = db.query(tabla, campos, null, null, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }

        if(c != null && c.moveToFirst()){
            do{
                pedido = new Pedido();
                for(int i=0; i<camposCount; i++){

                    pedido.setId(c.getLong(0));
                    Date fecha = Fechas.Convertir(c.getString(1));
                    pedido.setFecha(fecha);
                    Date fechaRecogida = Fechas.Convertir(c.getString(2));
                    pedido.setFechaRecogida(fechaRecogida);
                    Date horaRecogida = Fechas.ConvertirHora(c.getString(3));
                    pedido.setHoraRecogida(horaRecogida);

                    prodList = cargarProductosPedido(context, db, pedido);
                    pedido.setProductos(prodList);
                }
                pedidoList.add(pedido);
            }while(c.moveToNext());
        }
        c.close();
        return pedidoList;
    }

    public static Pedido cargarPedido(SQLiteDatabase db, Context context, long idPedido){

        String tabla = PEDIDO;
        Pedido pedido = new Pedido();
        ArrayList<Producto> prodList;
        String[] campos = pedido.getCampos();
        String[] args = {String.valueOf(idPedido)};
        String where = "_id = ?";
        int camposCount = campos.length;
        Cursor c = null;

        try {
            c = db.query(tabla, campos, where, args, null, null, null);

        }catch (final Exception e) {
            Log.d("tienda", "Error al cargar "+tabla+ " BD: ", e);
            Msg.mensaje(context, "Error", "Error al cargar "+tabla+ " desde BD: " + e.getMessage(), false);
        }

        if(c != null && c.moveToFirst()){

                pedido = new Pedido();
                for(int i=0; i<camposCount; i++){

                    pedido.setId(c.getLong(0));
                    Date fecha = Fechas.Convertir(c.getString(1));
                    pedido.setFecha(fecha);
                    Date fechaRecogida = Fechas.Convertir(c.getString(2));
                    pedido.setFechaRecogida(fechaRecogida);
                    Date horaRecogida = Fechas.ConvertirHora(c.getString(3));
                    pedido.setHoraRecogida(horaRecogida);

                    prodList = cargarProductosPedido(context, db, pedido);
                    pedido.setProductos(prodList);
                }
        }
        c.close();
        return pedido;
    }

    //////////////////// Lista /////////////////////////////////

}
