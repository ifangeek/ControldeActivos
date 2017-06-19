package cibertec.controldeactivos;

import android.database.sqlite.SQLiteDatabase;

public class ClaseBase_SD {
    private String _RutaBaseDatos="/data/data/cibertec.registroproveedor/controlactivos.db";
    public ClaseBase_SD()
    {
    }
    public void CrearBaseDatos()
    {
        SQLiteDatabase odb=SQLiteDatabase.openDatabase(_RutaBaseDatos,null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        odb.execSQL("CREATE TABLE IF NOT EXISTS ACTIVO(\n" +
                "idactivo INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "nombre VARCHAR(80)  NULL,\n" +
                "tipo VARCHAR(80)  NULL,\n" +
                "marca VARCHAR(50)  NULL);\n"+
                "modelo VARCHAR(50) NULL;\n");
        odb.close();
    }

    public String RutaBaseDatos()
    {
        return _RutaBaseDatos;
    }
}
