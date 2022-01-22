package com.guido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guido.Controller.LoginController;
import com.guido.Controller.ILoginController;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    ILoginController loginController = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmailField);
        password = findViewById(R.id.loginPassField);
        login = findViewById(R.id.submitLoginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginController.verifyLogin(email.getText().toString().trim(), password.getText().toString().trim()))
                    startActivity(new Intent(LoginActivity.this, MapsActivity.class));
                else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}