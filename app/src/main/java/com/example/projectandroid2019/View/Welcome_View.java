package com.example.projectandroid2019.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.WindowManager;


import com.example.projectandroid2019.R;
import com.example.projectandroid2019.View.V_Login.Login_View;



public class Welcome_View extends AppCompatActivity {

    private final int DURACION_SPLASH = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome__view);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(Welcome_View.this, Login_View.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}