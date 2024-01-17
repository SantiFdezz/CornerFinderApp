package com.example.cornerfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText password2EditText;

    private EditText emailEditText;
    private EditText birthdateEditText;
    private Button registerButton;
    private RequestQueue requestQueue;
    private Context context = this;
    private Button registerPage;
    private Button loginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        password2EditText = findViewById(R.id.password2);
        emailEditText = findViewById(R.id.email);
        birthdateEditText = findViewById(R.id.birthdate);
        registerButton = findViewById(R.id.register_button);
        registerPage = findViewById(R.id.register_page);
        loginPage = findViewById(R.id.login_page);
        requestQueue = Volley.newRequestQueue(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String password2 = password2EditText.getText().toString();
                String email = emailEditText.getText().toString();
                String birthdate = birthdateEditText.getText().toString();
                 if (validateRegister(username,password,password2,email,birthdate)){
                     sendRegisterRequest(username, password, email, birthdate);
                 }
            }
        });
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    private boolean validateRegister(String username, String password,String password2, String email, String birthdate){
        if (username.isEmpty() || password.isEmpty() || password2.isEmpty() || email.isEmpty() ||birthdate.isEmpty()){
            Toast.makeText(this, "Debes rellenar todos los campos!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(password2)){
            passwordEditText.setError("Las contraseñas deben coincidir");
            password2EditText.setError("Las contraseñas deben coincidir");
            return false;
        }
        if (!email.contains("@") || email.length() < 8){
            emailEditText.setError("Formato inválido de email");
            return false;
        }
        if (birthdate.length() != 10){
            birthdateEditText.setError("Formato inválido de fecha de nacimiento");
            return false;
        }
        return true;
    }
    private void sendRegisterRequest(String username, String password, String email, String birthdate) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username", username);
            requestBody.put("password", password);
            requestBody.put("email", email);
            requestBody.put("birthdate", birthdate);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Server.name + "/user",
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context, "Usuario creado", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            Toast.makeText(context, "No se pudo establecer la conexión", Toast.LENGTH_LONG).show();
                        } else {
                            int serverCode = error.networkResponse.statusCode;
                            Toast.makeText(context, "Estado de respuesta: " + serverCode, Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
        this.requestQueue.add(request);
    }
}