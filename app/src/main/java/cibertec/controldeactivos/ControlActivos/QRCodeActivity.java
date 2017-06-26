package cibertec.controldeactivos.ControlActivos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cibertec.controldeactivos.R;


public class QRCodeActivity extends AppCompatActivity {

     public TextView contenido ;
     TextView formato ;

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the main content layout of the Activity
        setContentView(R.layout.activity_qrcode);;
        contenido = (TextView) findViewById(R.id.txtContent);
        formato = (TextView)findViewById(R.id.txtFormat);


    }

    //product barcode mode
    public void scanBar(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(QRCodeActivity.this, "Scaner no encontrado", "Descargar el scanner code activity?", "Si", "No").show();
        }
    }

    //product qr code mode
    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(QRCodeActivity.this, "Scaner no encontrado", "Descargar el scanner code activity?", "Si", "No").show();
        }
    }

    //DIALOGO PARA DESCARGAR LA Aplicacion
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    // METODO QUE DEVUELVE UN RESULTADO DEL SCANEO
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent

                String contents = intent.getStringExtra("SCAN_RESULT");
                    Log.v("CONTENIDO :",""+contents);
                contenido.setText(contents);
                    //LOG.E SE GENERA DENTRO DE UN CATCH PARA HACER UN PRINT EN CONSOLA DE COLOR ROJO PARA VERIFICAR ALGÚN ERROR.
                    //Log.e("QRCODE",e.getMessage());



                    Log.v("CONTENIDO :",""+contenido);
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                formato.setText(format);

                    Log.v("FORMATO :",""+formato.toString());
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
