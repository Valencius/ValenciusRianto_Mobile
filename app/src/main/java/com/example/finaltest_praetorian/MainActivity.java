package com.example.finaltest_praetorian;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailField, passwordField;
    Button loginButton;
    TextView registerLink;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.et_email);
        passwordField = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.login_btn);
        registerLink = findViewById(R.id.register_link);

        mAuth = FirebaseAuth.getInstance();

        registerLink.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        });

        loginButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, task -> {
                if(!task.isSuccessful()){
                    Toast.makeText(this, "Login Failed "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                }
            });
        });
    }
}