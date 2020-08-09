package com.example.amst7;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatosUsuarios) {
        String query="create table usuarios( usuario text primary key, nombre text, apellido text, text pass, correo text, celular text, categoriafav text, sexo text);";
        Log.d("hola",query);
        BaseDeDatosUsuarios.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //METODO PARA ABRIR LA BD
    public void abrir(){
        this.getWritableDatabase();
    }

    //METODO PARA CERRAR LA BD
    public void cerrar(){
        this.close();
    }

    public void insertarregistro(String usuario, String nombre, String apellido, String pass, String correo, String celular, String categoriafav, String sexo){
        ContentValues registro=new ContentValues();
        registro.put("usuario",usuario);
        registro.put("nombre",nombre);
        registro.put("apellido",apellido);
        registro.put("pass",pass);
        registro.put("correo",correo);
        registro.put("celular",celular);
        registro.put("categoriafav",categoriafav);
        registro.put("sexo",sexo);
        this.getWritableDatabase().insert("usuarios",null,registro);
    }

    public Cursor validar(String usuario, String contra) throws SQLException {
        Cursor mcursor=null;
        mcursor=this.getReadableDatabase().query("usuarios", new String[]{"usuario","nombre","apellido","pass","correo","celular","categoriafav","sexo"},
                "usuario like '"+usuario+"'"+ "and pass like '"+contra+"'",null,null,null,null);
        return mcursor;
    }
}
