package cibertec.controldeactivos;

import java.io.Serializable;

/**
 * Created by DIEGO on 4/06/2017.
 *///codigo,nombre,tipo,marca,modelo

public class Activo_BE implements Serializable{
    private int Codigo;
    private String Nombre;
    private String Tipo;
    private String Marca;
    private String Modelo;
    private int Stock;

    public static int CAMPO_CODIGO = 0;
    public static int CAMPO_NOMBRE = 1;

    public Activo_BE(int codigo, String nombre, String tipo, String marca, String modelo, int stock) {
        Codigo = codigo;
        Nombre = nombre;
        Tipo = tipo;
        Marca = marca;
        Modelo = modelo;
        Stock = stock;
    }

    public Activo_BE() {
        Codigo = 0;
        Nombre  = "";
        Tipo = "";
        Marca = "";
        Modelo = "";
        Stock = 0;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getStock() { return Stock;  }

    public void setStock(int stock) { Stock = stock;  }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

}
