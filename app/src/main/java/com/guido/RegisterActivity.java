package com.guido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText username, email, password;
    Button submitRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.registerUserField);
        email = findViewById(R.id.registerEmailField);
        password = findViewById(R.id.registerPassField);
        submitRegister = findViewById(R.id.submitRegisterBtn);

        submitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO cenas de registar na bd e chamar respetiva funcao de controller
                //TODO verificacao de email e pass
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }
}