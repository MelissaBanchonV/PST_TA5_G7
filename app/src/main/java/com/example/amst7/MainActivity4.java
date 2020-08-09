package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity4 extends AppCompatActivity {
    private ListView lista;
    int [] datosImg = {R.drawable.imagen1,R.drawable.imagen2,R.drawable.imagen3, R.drawable.imagen4, R.drawable.imagen5, R.drawable.imagen6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        int contador = 0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("Select * from Libros", null);
        int tamano = fila.getCount();
        String[][] listado = new String[tamano][5];
        fila.moveToFirst();
        do {
            String[] datos_libro = new String[5];
            datos_libro[0] = fila.getString(1);
            datos_libro[1] = fila.getString(3);
            datos_libro[2] = fila.getString(4);
            datos_libro[3] = fila.getString(2);
            datos_libro[4] = fila.getString(5);
            listado[contador] = datos_libro;
            contador +=1;
        } while (fila.moveToNext());
        BaseDeDatos.close();
        admin.close();

        lista = (ListView)findViewById(R.id.id_listView);
        lista.setAdapter(new Adaptador(this, listado, datosImg));
    }

    public void cerrar(View view){
        finish();
    }

}