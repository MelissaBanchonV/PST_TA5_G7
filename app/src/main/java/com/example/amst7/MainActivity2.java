package com.example.amst7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public EditText etnombre, etapellido, etusuarioreg, etcontraus, etcorreo, etcell, etcategoriafav;
    public String strsexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        etnombre=(EditText)findViewById(R.id.editTextNombre);
        etapellido=(EditText)findViewById(R.id.editTextApellido);
        etusuarioreg=(EditText)findViewById(R.id.editTextNombUsu);
        etcontraus=(EditText)findViewById(R.id.editTextContra);
        etcorreo=(EditText)findViewById(R.id.editTextEmail);
        etcell=(EditText)findViewById(R.id.editTextCell);
        etcategoriafav=(EditText)findViewById(R.id.editTextCatFav);
        strsexo=spinner.getSelectedItem().toString();
    }
    public void confirmar(View view){
        registro(view);
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void registro(View view){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase Basedatos=admin.getWritableDatabase();
        String nombre=etnombre.getText().toString();
        String apellido=etapellido.getText().toString();
        String userreg=etusuarioreg.getText().toString();
        String contra=etcontraus.getText().toString();
        String correo=etcorreo.getText().toString();
        String celular=etcell.getText().toString();
        String categ=etcategoriafav.getText().toString();
        String sexo=strsexo;

        if(!nombre.isEmpty() && !apellido.isEmpty() && !userreg.isEmpty()
            && !contra.isEmpty() && !correo.isEmpty() && !celular.isEmpty()
            && !categ.isEmpty() && !sexo.isEmpty()){
            ContentValues registro=new ContentValues();
            registro.put("usuario",userreg);
            registro.put("nombre",nombre);
            registro.put("apellido",apellido);
            registro.put("correo",correo);
            registro.put("celular",celular);
            registro.put("categoriafav",categ);
            registro.put("sexo",sexo);

            Basedatos.insert("usuarios", null, registro);
            etnombre.setText("");
            etapellido.setText("");
            etusuarioreg.setText("");
            etcontraus.setText("");
            etcorreo.setText("");
            etcell.setText("");
            etcategoriafav.setText("");
            strsexo="";
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}