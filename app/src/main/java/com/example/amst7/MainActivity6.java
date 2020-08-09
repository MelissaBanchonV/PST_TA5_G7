package com.example.amst7;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
public class MainActivity6 extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ImageButton exit = (ImageButton)findViewById(R.id.imageButton);
        exit.setOnClickListener((View.OnClickListener)this);
    }
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity6.this);
        builder.setMessage("¿Seguro que desea salir?");
        builder.setCancelable(true);
        builder.setNegativeButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialogAlerta = builder.create();
        dialogAlerta.show();
    }
    public void avatarUsuario(){
    }

}