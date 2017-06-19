package cibertec.controldeactivos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

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

    }
}
