package cibertec.controldeactivos.ControlActivos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cibertec.controldeactivos.AdaptadorActivos;
import cibertec.controldeactivos.Data.Activos.Activo_SD;
import cibertec.controldeactivos.Entities.Activo_BE;
import cibertec.controldeactivos.Data.FuncionesComunes;
import cibertec.controldeactivos.Data.Activos.NuevoActivo;
import cibertec.controldeactivos.R;

public class MainActivity extends AppCompatActivity {
    private AdaptadorActivos oAdaptadorActivos = null;
    private ArrayList<Activo_BE> oListaActivo = null;
    ListView oVistaLista = null;
    ProgressDialog progrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oListaActivo = new Activo_SD().Listar(new Activo_BE());
        oAdaptadorActivos = new AdaptadorActivos(this,R.layout.activity_main,oListaActivo);
        oVistaLista = (ListView)findViewById(R.id.lstListaactivo);
        oVistaLista.setAdapter(oAdaptadorActivos);
        oVistaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activo_BE  oActivoSeleccionado = oListaActivo.get(position);
                Intent oIntento = new Intent(getBaseContext(),NuevoActivo.class);
                oIntento.putExtra("TIPOTRANSACCION", FuncionesComunes.ACTUALIZARREGISTRO);
                Log.v("FUNCION COMUN ","ACTUALIZAR");
                oIntento.putExtra("OBJACTIVO",oActivoSeleccionado);
                startActivityForResult(oIntento,1);
                ;            }
        });

    }

    public void BuscarActivo(View v)
    {
        EditText oEdtNombre = (EditText)findViewById(R.id.edtNombreActivo);
        Activo_BE oActivo_BE = new Activo_BE();
        oActivo_BE.setNombre(oEdtNombre.getText().toString());
        oListaActivo = new Activo_SD().Listar(oActivo_BE);
        oAdaptadorActivos = new AdaptadorActivos(this,R.layout.activity_main,oListaActivo);
        oVistaLista = (ListView)findViewById(R.id.lstListaactivo);
        oVistaLista.setAdapter(oAdaptadorActivos);
        Toast.makeText(getBaseContext(), String.valueOf(oListaActivo.size()) + " registros encontrados. " ,
                Toast.LENGTH_LONG).show();
    }

    public void NuevoActivo(View v)
    {
        Toast.makeText(getBaseContext(),"Nuevo Activo",
                Toast.LENGTH_LONG).show();
        Intent oIntento = new Intent(getBaseContext(),NuevoActivo.class);
        oIntento.putExtra("TIPOTRANSACCION",FuncionesComunes.NUEVOREGISTRO);
        oIntento.putExtra("OBJACTIVO",new Activo_BE());
        startActivityForResult(oIntento,1);
    }

    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent data) {
        if(RequestCode == 1)
        {
            if(ResultCode == RESULT_OK)
            {
                BuscarActivo(null);
                Log.v("LISTA TOTAL","");
            }


        }
    }

    public void CargarNuevoActivo(Activo_BE pActivo)
    {
        oListaActivo.add(pActivo);
        oAdaptadorActivos = new AdaptadorActivos(this,R.layout.activity_main,
                oListaActivo);
        oVistaLista.setAdapter(oAdaptadorActivos);
        Log.v("NUEVO ACTIVO","");
    }




}
