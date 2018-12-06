package com.github.jaidenrm.myanimalapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setup();
    }

    private void setup() {
        email = findViewById(R.id.login_etEmail);
        password = findViewById(R.id.login_etPassword);
        login = findViewById(R.id.login_buttonLogin);

        initFirebase();
        initButton();
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
    }

    private void initButton() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length() > 0 && password.getText().length() > 0) {
                    auth.signInWithEmailAndPassword(email.toString(), password.toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Intent homeScreen = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(homeScreen);
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "Login FAILED", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
