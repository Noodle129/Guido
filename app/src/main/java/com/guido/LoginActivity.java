package com.guido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guido.Controller.Controller;
import com.guido.Controller.IController;
import com.guido.Exceptions.InvalidCredentials;
import com.guido.Model.User;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    IController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controller = (IController) getIntent().getSerializableExtra("controller");
        email = findViewById(R.id.loginEmailField);
        password = findViewById(R.id.loginPassField);
        login = findViewById(R.id.submitLoginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    User u = controller.verifyLogin(email.getText().toString().trim(), password.getText().toString().trim());
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, NavigationDrawer.class));
                } catch (InvalidCredentials e) {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

