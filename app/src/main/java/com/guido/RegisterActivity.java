package com.guido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guido.Controller.IController;
import com.guido.Exceptions.EmailNotAvalable;
import com.guido.Utilities.Tools;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameText, emailText, passwordText;
    Button submitRegister;
    IController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        controller = (IController) getIntent().getSerializableExtra("controller");
        usernameText = findViewById(R.id.registerUserField);
        emailText = findViewById(R.id.registerEmailField);
        passwordText = findViewById(R.id.registerPassField);
        submitRegister = findViewById(R.id.submitRegisterBtn);

        submitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRegister.setOnClickListener(l -> {
                    String email = emailText.getText().toString().trim();
                    String username = usernameText.getText().toString().trim();
                    String password = passwordText.getText().toString().trim();
                    if (Tools.isValidEmailAddress(email)) {
                        try {
                            controller.registerUser(email, password, username);
                            Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        } catch (EmailNotAvalable e) {
                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}