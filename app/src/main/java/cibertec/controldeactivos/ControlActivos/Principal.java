package cibertec.controldeactivos.ControlActivos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import cibertec.controldeactivos.R;

public class Principal extends AppCompatActivity {


    ImageButton imgBMant;
    ImageButton imgBCodQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);



        imgBMant = (ImageButton)findViewById(R.id.imgMantAct);
        imgBCodQR = (ImageButton)findViewById(R.id.imgCodQR);

        imgBMant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Ventana Mantenimiento",Toast.LENGTH_SHORT).
                        show();

                Intent oIntento = new Intent(getBaseContext(),MainActivity.class);
                startActivity(oIntento);
            }


        });

        imgBCodQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Ventana QR",Toast.LENGTH_SHORT).
                        show();

                Intent oIntento1 = new Intent(getBaseContext(),QRCodeActivity.class);
                startActivity(oIntento1);

            }
        });





    }






}
