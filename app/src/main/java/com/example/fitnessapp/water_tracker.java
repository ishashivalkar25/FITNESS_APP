package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import java. util. Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class water_tracker extends AppCompatActivity {
    private int progr=0;
    private int count=0;
    ProgressBar progress_bar;
    TextView text_view_progress;
    TextView info;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tracker);
        ImageButton addition=findViewById(R.id.addition);
        ImageButton sub=findViewById(R.id.sub);
        progress_bar = findViewById(R.id.progress_bar);
        text_view_progress = findViewById(R.id.text_view_progress);
        info = findViewById(R.id.info);
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                progr = 0;
//                count = 0;
//            }
//        },86400000);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progr <=90) {
                    progr += 10;
                    count += 1;
                    updateProgressBar();
                    info.setText(count +" out of 10 glasses");
                }
                else if(progr >= 100){
                    count ++;
                    info.setText(count +" out of 10 glasses");
                }
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progr >=10 && count <=10) {
                    progr -= 10;
                    count -= 1;
                    updateProgressBar();
                    info.setText(count +" out of 10 glasses");
                }
                if(progr == 100 && count > 10)
                {
                    count --;
                    info.setText(count +" out of 10 glasses");
                }
                else if(count > 10){
                    count --;
                    info.setText(count +" out of 10 glasses");
                }
            }
        });
    }
    private void updateProgressBar(){
        progress_bar.setProgress(progr);
        text_view_progress.setText(progr+"%");
        if(progr==100){
            Toast.makeText(water_tracker.this, "Completed your water goal today", Toast.LENGTH_SHORT).show();
        }
    }
}