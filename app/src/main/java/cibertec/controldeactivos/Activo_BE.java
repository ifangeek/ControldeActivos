package cibertec.controldeactivos;

import java.io.Serializable;

/**
 * Created by DIEGO on 4/06/2017.
 */

public class Activo_BE implements Serializable{
    private int Codigo;
    private String Nombre;
    private int Tipo;
    private String Ubicacion;
    private int Marca;
    private int Modelo;

    public static int CAMPO_CODIGO = 0;
    public static int CAMPO_NOMBRE = 1;

    public Activo_BE(int pcodigo, String pnombre, int ptipo, String pubicacion, int pmarca, int pmodelo) {
        Codigo = pcodigo;
        Nombre = pnombre;
        Tipo = ptipo;
        Ubicacion = pubicacion;
        Marca = pmarca;
        Modelo = pmodelo;
    }

    public int getCodigo() { return Codigo;  }

    public void setCodigo(int codigo) {  Codigo = codigo;   }

    public String getNombre() { return Nombre;   }

    public void setNombre(String nombre) {  Nombre = nombre;  }

    public int getTipo() { return Tipo; }

    public void setTipo(int tipo) {  Tipo = tipo;  }

    public String getUbicacion() { return Ubicacion; }

    public void setUbicacion(String ubicacion) {  Ubicacion = ubicacion;   }

    public int getMarca() { return Marca;   }

    public void setMarca(int marca) { Marca = marca;    }

    public int getModelo() { return Modelo;   }

    public void setModelo(int modelo) { Modelo = modelo;   }

    public Activo_BE()
    {
        Codigo = 0;
        Nombre = "";
        Tipo = 0;
        Ubicacion = "";
        Marca = 0;
        Modelo = 0;


    }
}
