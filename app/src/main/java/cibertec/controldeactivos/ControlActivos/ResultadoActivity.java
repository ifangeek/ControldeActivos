package cibertec.controldeactivos.ControlActivos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cibertec.controldeactivos.R;

public class ResultadoActivity extends AppCompatActivity {

    TextView _texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        _texto = (TextView)findViewById(R.id.texto);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null)
        {
            String dato = extras.getString("DATO");
            _texto.setText(dato);
        }

    }

}
