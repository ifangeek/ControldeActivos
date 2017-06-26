package cibertec.controldeactivos;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * Created by DIEGO on 23/06/2017.
 */

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
          "Tu seleccionaste :" + parent.getItemAtPosition(position).toString()
                , Toast.LENGTH_SHORT).show();
        parent.getItemAtPosition(position);
        String spinner= null;
        spinner = parent.getItemAtPosition(position).toString();
        Log.v("SPINNER SELECCIONADO"," :"+spinner);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
