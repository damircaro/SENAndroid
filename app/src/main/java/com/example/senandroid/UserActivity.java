package com.example.senandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    TextView tvNombre,tvCedula,tvCelular,tvCorreo,tvCreated;
    Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        tvNombre = findViewById(R.id.tvNombre);
        tvCedula = findViewById(R.id.tvCedula);
        tvCelular = findViewById(R.id.tvCelular);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvCreated = findViewById(R.id.tvCreated);
        btnSalir = findViewById(R.id.btnSalir);

        getUser();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salir();

            }
        });
    }



    private void getUser() {
        tvNombre.setText("NOMBRE : -");
        tvCedula.setText("CEDULA : -");
        tvCelular.setText("CELULAR : -");
        tvCorreo.setText("CORREO : -");
        tvCreated.setText("CREADO : -");
               

    }

    private void Salir() {
        Intent intent= new Intent(UserActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();


    }
}