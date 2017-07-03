package cibertec.controldeactivos.Data;

import android.database.sqlite.SQLiteDatabase;

public class ClaseBase_SD {
    private String _RutaBaseDatos="/data/data/cibertec.controldeactivos/controlactivos.db";

    public ClaseBase_SD()
    {
    }
    public void CrearBaseDatos()
    {
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(_RutaBaseDatos,null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        odb.execSQL("CREATE TABLE IF NOT EXISTS ACTIVO(\n" +
                "CODIGO INTEGER  NOT NULL PRIMARY KEY ,\n" +
                "NOMBRE TEXT  NULL,\n" +
                "TIPO TEXT  NULL,\n" +
                "MARCA TEXT  NULL,\n"+
                "MODELO TEXT NULL,\n"+
                "STOCK INTEGER NULL);\n");
        odb.close();
    }

    public void CrearBDPruebaCombo()
    {
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(_RutaBaseDatos,null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        odb.execSQL("CREATE TABLE IF NOT EXISTS TIPO(\n)"+
                    "IDTIPO INTEGER NOT NULL PRIMARY KEY, \n"+
                    "NOMTIPO TEXT NULL);\n");
        odb.close();
    }

    public String RutaBaseDatos()
    {
        return _RutaBaseDatos;
    }
}
