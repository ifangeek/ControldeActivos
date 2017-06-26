package cibertec.controldeactivos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DIEGO on 4/06/2017.
 */

public class AdaptadorActivos extends ArrayAdapter<Activo_BE> {
    private ArrayList<Activo_BE> ListaInterna;

    public AdaptadorActivos(Context context,
                            int textViewResourceId, ArrayList<Activo_BE> items) {
        super(context, textViewResourceId, items);
        this.ListaInterna = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View oLayoutRegistro = convertView;
        if (oLayoutRegistro == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            oLayoutRegistro = vi.inflate(R.layout.registroactivo,
                    null);
        }
        //Obtengo la posicion del activo
        Activo_BE oActivo = ListaInterna.get(position);
        if (oActivo != null) {
            TextView oIdActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtIDActivo);
            TextView oNombreActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtNombreActivo);
            TextView oTipoActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtTipoActivo);
            TextView oMarcaActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtMarca);
            TextView oModeloActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtModelo);
            TextView oStockActivo = (TextView) oLayoutRegistro.findViewById(R.id.TxtStock);

            if (oIdActivo != null) {
                //de la posicion
                oIdActivo.setText(String.valueOf(oActivo.getCodigo()));
            }
            if (oNombreActivo != null) {
                oNombreActivo.setText(oActivo.getNombre());
            }
            if (oTipoActivo != null) {
                oTipoActivo.setText(oActivo.getTipo());
            }
            if (oMarcaActivo != null) {
                oMarcaActivo.setText(oActivo.getMarca());
            }
            if (oModeloActivo != null) {
                oModeloActivo.setText(oActivo.getModelo());
            }
            if (oStockActivo != null) {
                oStockActivo.setText(String.valueOf(oActivo.getStock()));
            }
        }

            return oLayoutRegistro;

        }


}

