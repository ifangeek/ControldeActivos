package cibertec.controldeactivos;

import java.io.Serializable;

/**
 * Created by DIEGO on 4/06/2017.
 */

public class Activo_BE implements Serializable{
    private int Codigo;
    private String Nombre;
    private String Tipo;
    private String Ubicacion;
    private String Marca;
    private String Modelo;

    public static int CAMPO_CODIGO = 0;
    public static int CAMPO_NOMBRE = 1;

    public Activo_BE(int codigo, String nombre, String tipo, String ubicacion, String marca, String modelo) {
        Codigo = codigo;
        Nombre = nombre;
        Tipo = tipo;
        Ubicacion = ubicacion;
        Marca = marca;
        Modelo = modelo;
    }

    public Activo_BE() {
        Codigo = 0;
        Nombre  = "";
        Tipo = "";
        Ubicacion = "";
        Marca = "";
        Modelo = "";
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

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

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
