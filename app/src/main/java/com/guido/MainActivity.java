package com.guido;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guido.Controller.Controller;
import com.guido.Controller.IController;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private IController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller();
    }

    public void launchRegister(View v){
        Intent i = new Intent(this, RegisterActivity.class);
        i.putExtra("controller", (Serializable) controller);
        startActivity(i);
    }

    public void launchLogin(View v){
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("controller", (Serializable) controller);
        startActivity(i);
    }
}




