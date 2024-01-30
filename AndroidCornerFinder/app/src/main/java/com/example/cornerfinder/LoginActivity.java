package com.example.cornerfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private Button registerButton, loginButton, accessButton;
    private EditText textUsername, textPassword;
    private Context context = this;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        accessButton = findViewById(R.id.access_button);
        registerButton = findViewById(R.id.register_page);
        loginButton = findViewById(R.id.login_page);
        textUsername = findViewById(R.id.campo_email);
        textPassword = findViewById(R.id.campo_contraseña);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
            }
        });

        accessButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = textUsername.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Authentication failed."+ task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}


