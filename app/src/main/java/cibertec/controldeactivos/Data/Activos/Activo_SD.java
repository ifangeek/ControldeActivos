package cibertec.controldeactivos.Data.Activos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import cibertec.controldeactivos.Data.ClaseBase_SD;
import cibertec.controldeactivos.Entities.Activo_BE;
import cibertec.controldeactivos.Data.FuncionesComunes;

/**
 * Created by DIEGO on 17/06/2017.
 */

public class Activo_SD extends ClaseBase_SD {
    private String _NombreTabla = " ACTIVO ";


    //Crear base de datos
    public Activo_SD(){this.CrearBaseDatos();}

    //Recorrer y llenar el ArrayList de todos los activos que se encuentren en la BD
    //mediante el Cursor a la TablaActivo
   public ArrayList<Activo_BE> Listar(Activo_BE pActivo_BE)
    {
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(this.RutaBaseDatos(),
                    null,SQLiteDatabase.CREATE_IF_NECESSARY);

        Cursor TablaActivo = odb.rawQuery("SELECT * FROM " + _NombreTabla +
            " WHERE NOMBRE LIKE '" + pActivo_BE.getNombre().toString() + "%'",null);
        ArrayList<Activo_BE> oLista = new ArrayList<>();
        if(TablaActivo.moveToFirst())
        {
            do {
                oLista.add(
                        new Activo_BE(
                                TablaActivo.getInt(0),
                                TablaActivo.getString(1),
                                TablaActivo.getString(2),
                                TablaActivo.getString(3),
                                TablaActivo.getString(4),
                                TablaActivo.getInt(5)
                                )
                );
            }while (TablaActivo.moveToNext());
        }
        Log.v("",""+oLista);
        return oLista;

    }




   public Activo_BE ListarKey(int pCodigo)
    {
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(this.RutaBaseDatos(),null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor TablaActivo = odb.rawQuery("SELECT * FROM " + _NombreTabla + " WHERE CODIGO ="+
                    String.valueOf(pCodigo),null);
        Activo_BE oActivo_BE = new Activo_BE(0,"","","","",0);
        if(TablaActivo.moveToFirst())
        {
            do{
                oActivo_BE = new Activo_BE(
                        TablaActivo.getInt(0),
                        TablaActivo.getString(1),
                        TablaActivo.getString(2),
                        TablaActivo.getString(3),
                        TablaActivo.getString(4),
                        TablaActivo.getInt(5));
            }while(TablaActivo.moveToNext());
        }
        TablaActivo.close();
        odb.close();
        return oActivo_BE;

    }

    //Permite seleccionar el ult. nro. de fila que haya en la tabla activo.
    public int ObtenerUiltimoCodigo() {
        int UltimoCodigoGenerado;
        UltimoCodigoGenerado = 0;
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(this.RutaBaseDatos(), null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor TablaActivo = odb.rawQuery("SELECT ROWID from " + _NombreTabla +
                " ORDER BY ROWID DESC LIMIT 1", null);
        Activo_BE oActivo_BE = new Activo_BE(0, "", "", "", "",0);
        if (TablaActivo.moveToFirst()) {
            do {
                UltimoCodigoGenerado = TablaActivo.getInt(0);
            } while (TablaActivo.moveToNext());
        }
        TablaActivo.close();
        odb.close();
        Log.v("COD",""+UltimoCodigoGenerado);
        return UltimoCodigoGenerado;
    }


    public long RegistraModifica(Activo_BE pActivo_BE, String pTipoTransaccion)
    {

        //idactivo,nombre,tipo,marca,modelo
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(this.RutaBaseDatos(),null,
                        SQLiteDatabase.CREATE_IF_NECESSARY);
        Log.v("XDA","Llego 1");
        ContentValues oValores = new ContentValues();
        oValores.put("CODIGO",pActivo_BE.getCodigo());
        oValores.put("NOMBRE",pActivo_BE.getNombre());
        oValores.put("TIPO",pActivo_BE.getTipo());
        oValores.put("MARCA",pActivo_BE.getMarca());
        oValores.put("MODELO",pActivo_BE.getModelo());
        oValores.put("STOCK",pActivo_BE.getStock());
        Log.v("XDA","Llego 2");
        long NumeroRegistro = 0;
        try {
            if(pTipoTransaccion.equals(FuncionesComunes.NUEVOREGISTRO))
            {
                Log.v("VALORES :",""+oValores);
                NumeroRegistro = odb.insert(_NombreTabla,null,oValores);
                Log.v("XDA","Llego 3");
            }
            else if(pTipoTransaccion.equals(FuncionesComunes.ACTUALIZARREGISTRO)) {
                String CodigoActivo = String.valueOf(pActivo_BE.getCodigo());
                String[] ValoresCamposCondicion = {CodigoActivo};
                NumeroRegistro = odb.update(_NombreTabla,oValores,"codigo=?",ValoresCamposCondicion);
                Log.v("XDA","Llego 4");
            }
            odb.close();
        }catch (Exception e)
        {
            Log.e("XDA:",""+e.getMessage().toString());


        }

        return NumeroRegistro;

    }

    public int Eliminar(int pCodigo)
    {
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(
                    this.RutaBaseDatos(),
                    null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
        String[] ValoresCamposCondicion = {String.valueOf(pCodigo)};
        int NumeroRegistro = odb.delete(_NombreTabla,"codigo=?",ValoresCamposCondicion);
        odb.close();
        return NumeroRegistro;

    }

    /*public int BuscarXQR(int codqr)
    {
        SQLiteDatabase odb = SQLiteDatabase.openDatabase(
                    this.RutaBaseDatos(),
                    null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
        
        )
    }*/

}
