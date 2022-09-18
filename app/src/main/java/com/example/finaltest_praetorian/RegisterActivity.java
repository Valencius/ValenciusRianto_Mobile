package com.example.finaltest_praetorian;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText emailField,usernameField, passwordField,confPassField;
    Button registerButton;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userReference;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginLink = findViewById(R.id.login_link);
        emailField = findViewById(R.id.et_email);
        usernameField = findViewById(R.id.et_username);
        passwordField = findViewById(R.id.et_password);
        confPassField = findViewById(R.id.et_confirmPassword);
        registerButton = findViewById(R.id.register_btn);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = firebaseDatabase.getInstance("https://finaltest-praetorian-default-rtdb.asia-southeast1.firebasedatabase.app/");

        loginLink.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        });

        registerButton.setOnClickListener(view -> {
            String email = emailField.getText().toString();
            String name = usernameField.getText().toString();
            String password = passwordField.getText().toString();
            String confirmPass = confPassField.getText().toString();

            if(!email.contains("@") || !email.endsWith(".com")){
                Toast.makeText(this, "Email must contain '@' and ends with '.com'", Toast.LENGTH_SHORT).show();
            }else if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            }else if(!password.equals(confirmPass)){
                Toast.makeText(this, "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
            }else if(password.length() < 6){
                Toast.makeText(this, "Password length must be more than 5 characters", Toast.LENGTH_SHORT).show();
            }else if(name.length() < 5){
                Toast.makeText(this, "Name must be more than 4 characters", Toast.LENGTH_SHORT).show();
            }
            else{
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, task -> {
                    if(!task.isSuccessful()){
                        Toast.makeText(this, "Register Failed, " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this, "Register Success, go Back to Login", Toast.LENGTH_SHORT).show();
                        userReference = firebaseDatabase.getReference().child("users").child(mAuth.getCurrentUser().getUid());
                        userReference.setValue(new User(name,email,password));
                    }

                });
            }
        });

    }
}