package com.example.miguel.appsalvaamigo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Miguel on 21/12/2017.
 */

public class ListaPerrosAdapter extends ArrayAdapter
{
    public ListaPerrosAdapter(Context context, List objects)
    {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.items_lista_perros,null);
        }

        ImageView imgPerro = (ImageView)convertView.findViewById(R.id.imgPerro);
        TextView txtNombre = (TextView)convertView.findViewById(R.id.txtNombrePerro);
        TextView txtEdad = (TextView)convertView.findViewById(R.id.txtEdadPerro);
        TextView txtRaza = (TextView)convertView.findViewById(R.id.txtRazaPerro);
        TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipoPerro);

        ItemsListaPerros itemsLista = (ItemsListaPerros)getItem(position);
        imgPerro.setImageResource(itemsLista.getImagen());
        txtNombre.setText(itemsLista.getNombre());
        txtEdad.setText(itemsLista.getEdad());
        txtRaza.setText(itemsLista.getRaza());
        txtTipo.setText(itemsLista.getTipo());

        return convertView;
    }
}
