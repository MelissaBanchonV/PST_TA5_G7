package com.example.amst7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class Adaptador extends BaseAdapter{

    private static LayoutInflater inflater = null;

    Context contexto;
    String [][] datos;
    int[] datosimg;
    int i;

    public Adaptador (Context contexto, String [][] datos, int[] imagenes){
        this.contexto = contexto;
        this.datos = datos;
        this.datosimg = imagenes;
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.elemento_lista, null);
        TextView titulo = (TextView)vista.findViewById(R.id.titulo);
        TextView autor = (TextView)vista.findViewById(R.id.autor);
        TextView editorial = (TextView)vista.findViewById(R.id.editorial);
        TextView genero = (TextView)vista.findViewById(R.id.genero);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView);


        titulo.setText(datos[i][0]);
        autor.setText(datos[i][1]);
        editorial.setText(datos[i][2]);
        genero.setText(datos[i][3]);
        imagen.setImageResource(datosimg[i]);

        return vista;
    }

    @Override
    public int getCount() {
        return datosimg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

}
