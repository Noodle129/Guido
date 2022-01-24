package com.guido;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;

import com.guido.Controller.Controller;
import com.guido.Controller.IController;
import com.guido.JDBC.JDBCQueries;

import java.io.Serializable;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private IController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new Controller();
        setContentView(R.layout.activity_main);
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




