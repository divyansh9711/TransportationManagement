package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.divyanshsingh.transportationmanagement.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Intent intent;
                    new AppController(SplashActivity.this);
                    if (mAuth == null || mAuth.getCurrentUser() == null) {
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        intent = new Intent(SplashActivity.this,SearchVehicleActivity.class);
                        startActivity(intent);
                    }
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        background.start();
    }
}


