package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText campousuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campousuario=(EditText)findViewById(R.id.nomusu);
    }
    public void registro(View view){
        Intent i=new Intent(this, MainActivity2.class);
        startActivity(i);
        finish();
    }

    public void validar(View view){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase Basedatosbase=admin.getWritableDatabase();
        String usuario=campousuario.getText().toString();
        if(!usuario.isEmpty()){
            Cursor fila=Basedatosbase.rawQuery("select usuario from usuarios where usuario = " +usuario,null);
            if (fila.moveToFirst()){

                /*Intent i=new Intent(this, MainActivity2.class);
                startActivity(i);
                finish();*/
                Toast.makeText(this, "Usuario correcto", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Usuario no existe, por favor registrese", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
        }
    }
}