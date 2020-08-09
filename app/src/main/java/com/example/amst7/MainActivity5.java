package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
    TextView titulo,descripcion;
    ImageView portada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        titulo = (TextView)findViewById(R.id.textViewTitulo_Libro);
        descripcion = (TextView)findViewById(R.id.textViewDescripcion_Libro);

        DisplayMetrics medidaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);
        int ancho = medidaVentana.widthPixels;
        int alto = medidaVentana.heightPixels;
        getWindow().setLayout((int)(ancho*0.8),(int)(alto*0.5));

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Intent intent = getIntent();
        String codigo_s = intent.getStringExtra(IngresoBaseDeDatos.EXTRA_MESSAGE);

        if (!codigo_s.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("Select Titulo,Descripcion from Libros where Codigo =1", null);
            if(fila.moveToFirst()){
                titulo.setText(fila.getString(0));
                descripcion.setText(fila.getString(1));
            }else{
                Toast.makeText(this,"No existe el artículo buscado",Toast.LENGTH_LONG).show();
            }
            BaseDeDatos.close();
        }else{
            Toast.makeText(this,"Ingrese un código",Toast.LENGTH_LONG).show();
        }
    }

    public void cerrar(View view){
        finish();
    }

}