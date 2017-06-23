package cibertec.controldeactivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NuevoActivo extends AppCompatActivity {
    //idactivo,nombre,tipo,marca,modelo
    EditText edtNombre =null;
    Spinner spTipo = null;
    Spinner spModelo = null;
    Spinner spMarca = null;
    EditText edtStock = null;

    Activo_BE _ActivoBe = null;
    String _TipoTransaccion ="N";
    Button oBtnGrabar = null;
    Button oBtnEliminar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_activo);


    }
}
