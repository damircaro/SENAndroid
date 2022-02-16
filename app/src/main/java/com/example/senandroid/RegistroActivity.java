package com.example.senandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Preconditions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {
    EditText etNombre, etCedula, etCelular, etPassword,etConfirmar, etEmail;
    Button btnRegistrar;
    String nombre ,cedula , celular ,contraseña, confirmacion,correo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etCedula = findViewById(R.id.etCedula);
        etCelular = findViewById(R.id.etCelular);
        etPassword = findViewById(R.id.etPassword);
        etConfirmar = findViewById(R.id.etConfirmar);
        etEmail = findViewById(R.id.etEmail);
        btnRegistrar= findViewById(R.id.Registrarse);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegistrarse();

            }
        });

    }

    private void checkRegistrarse() {
     nombre = etNombre.getText().toString();
     cedula = etCedula.getText().toString();
     celular = etCelular.getText().toString();
     contraseña = etPassword.getText().toString();
     confirmacion = etConfirmar.getText().toString();
     correo = etEmail.getText().toString();

     if(nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty()){
        alertFail("El nombre , Correo y Contraseña son requeridos");

     }
     else if (contraseña.equals(confirmacion)){
         alertFail("Contraseña y confirmación de contraseña no son las mismas");
     }
     else{
         sendRegister();
     }
    }

    private void sendRegister() {

        JSONObject params = new JSONObject();
        try {
            params.put("name",nombre);
            params.put("cedula",cedula);
            params.put("celular",celular);
            params.put("contraseña",contraseña);
            params.put("confirmacion",confirmacion);
            params.put("correo",correo);


        }catch (JSONException e){
            e.printStackTrace();
        }

        String data = params.toString();
        String url = getString(R.string.api_server)+"/register";

        new Thread(new Runnable() {
            @Override
            public void run() {

                Http http = new Http(RegistroActivity.this,url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code ==201 || code == 200){
                            alertSucces("Registro perfecto");
                        }else if (code == 422){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);

                            }catch (JSONException e){
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(RegistroActivity.this,"error"+code, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        }).start();

    }

    private void alertSucces(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Registrar")
                .setIcon(R.drawable.ic_check24)
                .setMessage(s)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.ic_warning)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                       dialogInterface.dismiss();

                    }
                }).show();

    }

}