package com.guido;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchRegister(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void launchLogin(View v){
        startActivity(new Intent(this, LoginActivity.class));
    }

}




