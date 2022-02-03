package com.example.senandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
   EditText etEmail, etPassword;
   Button btnLogin, btnRegistrarse;
   String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle(LOGIN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

    }
    private void checkLogin() {
        email = etEmail.getText().toString();
        password = etPassword.getText.toString();

        if (email.isEmpty() || password.isEmpty()) {
            alertFail("El numero de cedula y contraseña son requeridos");

        }else{
            sendLogin();
        }

    }

    private void sendLogin() {
        Toast.makeText(this, "Send", toast.LENGTH_SHORT).snow();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.ic_warning)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogo, int which) {

                        dialogo.dismiss();

                    }
                })

                .snow();

    }


}
