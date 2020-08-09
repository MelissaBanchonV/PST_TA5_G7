package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText campousuario;
    public EditText campopass;
    AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "datosregistro1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campousuario=(EditText)findViewById(R.id.nomusu);
        campopass= (EditText)findViewById(R.id.contras);
    }
    public void registro(View view){
        Intent i=new Intent(this, MainActivity2.class);
        startActivity(i);
        finish();
    }

    public void ingresar(View view){
        if(!(campousuario.getText().toString().isEmpty() || campousuario.getText().toString().isEmpty()) ){
            try {
                Cursor cursor = admin.validar(campousuario.getText().toString(),campopass.getText().toString());
                    if(cursor.getCount()>0){
                        Toast.makeText(this, "USUARIO EXISTE", Toast.LENGTH_SHORT).show();
                    }   else {
                        Toast.makeText(this, "Usuario no existe, por favor registrese", Toast.LENGTH_SHORT).show();
                    }
                    campousuario.setText("");
                    campopass.setText("");
                }

            catch(SQLException e){
                e.printStackTrace();
            }
        } else{
            Toast.makeText(this, "Ingrese Usuario", Toast.LENGTH_SHORT).show();
        }
    }
}