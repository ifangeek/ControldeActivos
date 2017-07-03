package cibertec.controldeactivos.Data.Activos;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cibertec.controldeactivos.Entities.Activo_BE;
import cibertec.controldeactivos.Data.FuncionesComunes;
import cibertec.controldeactivos.R;

public class NuevoActivo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //idactivo,nombre,tipo,marca,modelo
    EditText edtNombre =null;
    Spinner spnTipo,spnModelo,spnMarca;
    EditText edtStock = null;
   /* String[] tipo;*/
    String[] marcas;
  /*  String[] modelo;*/

    Activo_BE _Activo_BE = null;
    String _TipoTransaccion ="N";
    Button oBtnGrabar = null;
    Button oBtnEliminar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_activo);

        edtNombre= (EditText) findViewById(R.id.edtNombre);
        spnTipo = (Spinner)findViewById(R.id.spTipo);
        spnMarca = (Spinner)findViewById(R.id.spMarca);
        spnModelo = (Spinner)findViewById(R.id.spModelo);
        edtStock = (EditText)findViewById(R.id.edtStock);
        oBtnGrabar = (Button)findViewById(R.id.btnAgregar);
        oBtnEliminar = (Button)findViewById(R.id.btnEliminar);
        addItemsSpinnerTipo();
        cargarSpinnerMarcas();
        Bundle oDatos = getIntent().getExtras();
        _TipoTransaccion = oDatos.get("TIPOTRANSACCION").toString();
        Log.v("DATOS",oDatos.toString());
        _Activo_BE = (Activo_BE)oDatos.getSerializable("OBJACTIVO");

        MostrarDatos();


    }

    public void MostrarDatos()
    {
        if(_TipoTransaccion.equals(FuncionesComunes.NUEVOREGISTRO))
        {
            oBtnEliminar.setVisibility(View.INVISIBLE);
            oBtnGrabar.setText("GRABAR");
        }
        if(_TipoTransaccion.equals(FuncionesComunes.ACTUALIZARREGISTRO))
        {
            oBtnEliminar.setVisibility(View.VISIBLE);
            oBtnGrabar.setText("ACTUALIZAR");
        }
        edtNombre.setText(_Activo_BE.getNombre());
        Log.v("ACT.","1"+_Activo_BE.getNombre());
       /* spnMarca.getSelectedItem();
        spTipo.getSelectedItem().toString();

        spMarca.getSelectedItem().toString();
        spModelo.getSelectedItem().toString();*/
        edtStock.setText((""+ _Activo_BE.getStock()));
        Log.v("ACT.","2"+_Activo_BE.getStock());
    }

    public void AgregarActivo(View v)
    {
        Log.v("ENTROO","");
        Toast.makeText(getBaseContext(), "Agregando Activo",
                            Toast.LENGTH_SHORT).show();
        //OBTENERMOS EL NUEVO CODIGO DEL ACTIVO QUE SE AGREGARA
        int CodigoActivo = 0;

        Activo_SD oActivo_SD = new Activo_SD();
        if(_TipoTransaccion.equals(FuncionesComunes.NUEVOREGISTRO))
        {
            CodigoActivo = oActivo_SD.ObtenerUiltimoCodigo() + 1;
            Log.v("Codigo Nuevo",""+CodigoActivo);
        }
        //SI NO , OBTENDREMOS EL CODIGO QUE YA TIENE ESE ACTIVO
        else{
            CodigoActivo = _Activo_BE.getCodigo();
            Log.v("Codigo de la BD",""+CodigoActivo);
        }
        Log.v("COD NUEVO REG",""+CodigoActivo);


        /* VER QUE ME TRAE LOS SPINNER*/
      /* String  nombre_tipo = tipo[spTipo.getSelectedItemPosition()];
        Log.v("XDA",nombre_tipo);
        String  nombre_marca = marcas[spMarca.getSelectedItemPosition()]; // Ahi solo jalas el numero ahora tienes que pasarle eso a un origen de datos
        Log.v("XDA",nombre_marca);
        String  nombre_modelo = modelo[spModelo.getSelectedItemPosition()];
        Log.v("XDA",nombre_modelo);

*/
        Log.v("llego hasta aca","");
        Activo_BE oActivo_BE = new Activo_BE(
                CodigoActivo, edtNombre.getText().toString(),
                spnTipo.getSelectedItem().toString(),
               spnMarca.getSelectedItem().toString(),
               spnModelo.getSelectedItem().toString(),
                Integer.parseInt(edtStock.getText().toString()));
            Log.v("XDA", "PASS !");
            Long Resultado = oActivo_SD.RegistraModifica(oActivo_BE, _TipoTransaccion);

           Intent oRetorno = new Intent();
            oRetorno.putExtra("Activo",oActivo_BE);
            setResult(RESULT_OK,oRetorno);
            finish();

    }

    public void EliminarRegistro(View v)
    {
        AlertDialog.Builder oDialogo = new AlertDialog.Builder(this);
        oDialogo.setTitle("Confirmar Eliminación");
        oDialogo.setMessage("¿Estás seguro de eliminar el registro actual?");
        oDialogo.setCancelable(true);
        oDialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activo_SD  oActivo_SD  = new Activo_SD();
                oActivo_SD.Eliminar(_Activo_BE.getCodigo());
                Intent oRetorno = new Intent();
                oRetorno.putExtra("Activo",new Activo_BE());
                setResult(RESULT_OK,oRetorno);
                finish();
                dialog.cancel();
                Log.v("CONFIRMAR ELIMINACION","XDD");
            }
        }
        );

        oDialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog oConfirmar = oDialogo.create();
        oConfirmar.show();
    }

    public void cargarSpinnerMarcas()
    {
        //Mostrar lista de marcas que se encuentra en el R.Array.marca
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.MARCA,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Aplico el adaptador al Spinner.
        this.spnMarca.setAdapter(adapter);
        marcas = getResources().getStringArray(R.array.MARCA);
        this.spnMarca.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        Log.v("SPINNER",""+this.spnMarca.toString());


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()){
                case R.id.spMarca:
                    //Recibe un array
                    TypedArray arrayModelo = getResources().obtainTypedArray(
                            R.array.array_marca_a_modelo);
                    CharSequence[] modelo = arrayModelo.getTextArray(position);
                    arrayModelo.recycle();
                   /* modelo = getResources().getStringArray(R.array.array_marca_a_modelo);*/
                    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                            this,android.R.layout.simple_spinner_item,
                            android.R.id.text1,modelo);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    this.spnModelo.setAdapter(adapter);
                    break;
                case R.id.spModelo:
                    break;

            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void addItemsSpinnerTipo()
    {
        spnTipo = (Spinner)findViewById(R.id.spTipo);
        List<String> list = new ArrayList<String>();
        list.add("EQUIPO");
        list.add("AUTOMÓVIL");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        Log.v("SPINNER",""+list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spnTipo.setAdapter(dataAdapter);
        this.spnTipo.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            Log.v("XDA",""+spnTipo);


    }/*
    public void addItemsSpinnerMarca()
    {

        spMarca = (Spinner)findViewById(R.id.spMarca);
        List<String> list = new ArrayList<String>();
        list.add("KINGSTON");
        list.add("ASUS");
        list.add("TP-LINK");
        list.add("SAMSUNG");
        list.add("LOGITECH");
        list.add("LG");
        list.add("HP");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        Log.v("ADAPTER",""+dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMarca.setAdapter(dataAdapter);
    }*/



}
