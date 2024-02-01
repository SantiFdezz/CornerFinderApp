package com.example.cornerfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;


public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText, password2EditText, emailEditText, birthdateEditText;
    private Context context = this;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private FirebaseAuth mAuth;
    private Button registerPage, loginPage, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        password2EditText = findViewById(R.id.password2);
        emailEditText = findViewById(R.id.email);
        birthdateEditText = findViewById(R.id.birthdate);
        birthdateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterActivity.this,
                        dateSetListener,
                        year, month, day);
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1; // Month is 0-based, so we add 1
                String strMonth;
                if (month < 10) {
                    strMonth = "0" + month;
                } else {
                    strMonth = String.valueOf(month);
                }
                String date = day + "-" + strMonth + "-" + year;
                birthdateEditText.setText(date);
            }
        };
        registerButton = findViewById(R.id.register_button);
        registerPage = findViewById(R.id.register_page);
        loginPage = findViewById(R.id.login_page);
        registerButton.setOnClickListener(v -> registerUser());
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(){
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String password2 = password2EditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String birthdate = birthdateEditText.getText().toString().trim();
        birthdateEditText.setKeyListener(null);
        if (validateRegister(username,password,password2,email,birthdate)){
            mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task-> {
                        if (task.isSuccessful() ) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Usuario nuevoUser = new Usuario(username, email, birthdate);
                            FirebaseDatabase.getInstance().getReference("usuarios")
                                    .child(user.getUid())
                                    .setValue(nuevoUser)
                                    .addOnCompleteListener(taskDb -> {
                                if (taskDb.isSuccessful()) {
                                    Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Error al guardar datos:"+taskDb.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Log.e("TagError", task.getException().getMessage());
                            Toast.makeText(context, "Registro fallido"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
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
    static class Usuario {
        public String username, email, birthdate;

        public Usuario(String username, String email, String birthdate) {
            this.username = username;
            this.email = email;
            this.birthdate = birthdate;
        }
    }

}