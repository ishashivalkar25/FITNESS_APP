package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashscreenActivity extends AppCompatActivity {

    Animation up,down;

    TextView textview;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);



        imageview=findViewById(R.id.appsplash);
        up= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.up);
        imageview.setAnimation(up);

        textview=findViewById(R.id.appname);

        down=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down);
        textview.setAnimation(down);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        },4000);
    }
}