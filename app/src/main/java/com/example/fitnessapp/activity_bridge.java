package com.example.fitnessapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


public class activity_bridge extends AppCompatActivity {

    private Chronometer chrono;
    private long pauseOffset;
    private boolean running;
    Button skip;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);


        skip=findViewById(R.id.skip1);
        chrono = findViewById(R.id.chronometer);
        chrono.setFormat("     %s");
        running = true;

        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsed >= 31000) {
                    finish();
                }
            }
        });


        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        Log.i("chronometer",SystemClock.elapsedRealtime()+ " " +chrono.getBase());
        if((SystemClock.elapsedRealtime() - chrono.getBase()) == 31000)
        {
            finish();
        }

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    public void pauseChronometer(View v){
        if(running) {
            chrono.stop();
            pauseOffset=SystemClock.elapsedRealtime() - chrono.getBase();
            running=false;
        }
    }

    public void resumeChronometer(View v) {
        if(!running) {
            chrono.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chrono.start();
            running=true;
        }
    }

    public void restartChronometer(View v){
        chrono.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        chrono.start();
    }

}