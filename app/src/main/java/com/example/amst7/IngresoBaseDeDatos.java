package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresoBaseDeDatos extends AppCompatActivity {

    private EditText codigo,titulo,genero, autor, editorial, url_imagen,descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_base_de_datos);

        codigo = (EditText)findViewById(R.id.editTextCodigo);
        titulo = (EditText)findViewById(R.id.editTextTitulo);
        genero = (EditText)findViewById(R.id.editTextGenero);
        autor = (EditText)findViewById(R.id.editTextAutor);
        url_imagen = (EditText)findViewById(R.id.editTextImagen);
        descripcion = (EditText)findViewById(R.id.editTextDescripcion);
        editorial = (EditText)findViewById(R.id.editTextEditorial);
    }

    //Envio a la base de datos
    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo_s = codigo.getText().toString();
        String titulo_s = titulo.getText().toString();
        String genero_s = genero.getText().toString();
        String autor_s = autor.getText().toString();
        String url_imagen_s = url_imagen.getText().toString();
        String descripcion_s = descripcion.getText().toString();
        String editorial_s = editorial.getText().toString();

        if((!codigo_s.isEmpty()) && (!titulo_s.isEmpty()) && (!genero_s.isEmpty()) && (!autor_s.isEmpty()) && (!url_imagen_s.isEmpty()) && (!descripcion_s.isEmpty())){
            ContentValues registro = new ContentValues();
            registro.put("Codigo",codigo_s);
            registro.put("Titulo",titulo_s);
            registro.put("Genero",genero_s);
            registro.put("Autor",autor_s);
            registro.put("Editorial",editorial_s);
            registro.put("Descripcion",descripcion_s);
            registro.put("Imagen",url_imagen_s);

            BaseDeDatos.insert("Libros", null, registro);
            BaseDeDatos.close();

            codigo.setText("");
            titulo.setText("");
            genero.setText("");
            autor.setText("");
            url_imagen.setText("");
            descripcion.setText("");
            editorial.setText("");

            Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Deben llenarse todos los campos",Toast.LENGTH_LONG).show();
        }
    }

    //Consulta a la base de datos
    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String codigo_s = codigo.getText().toString();

        if (!codigo_s.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("Select * from Libros where Codigo ="+codigo_s, null);
            if(fila.moveToFirst()){
                titulo.setText(fila.getString(1));
                genero.setText(fila.getString(2));
                autor.setText(fila.getString(3));
                editorial.setText(fila.getString(4));
                descripcion.setText(fila.getString(5));
                url_imagen.setText(fila.getString(6));
            }else{
                Toast.makeText(this,"No existe el artículo buscado",Toast.LENGTH_LONG).show();
            }
            BaseDeDatos.close();
        }else{
            Toast.makeText(this,"Ingrese un código",Toast.LENGTH_LONG).show();
        }
    }
}