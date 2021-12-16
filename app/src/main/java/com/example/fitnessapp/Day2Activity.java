package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Day2Activity extends AppCompatActivity {
    int action=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day2);

        Button start1 = findViewById(R.id.start1);
        Button start2 = findViewById(R.id.start2);
        Button start3 = findViewById(R.id.start3);
        Button start4 = findViewById(R.id.start4);
        Button start5 = findViewById(R.id.start5);
        Button start6 = findViewById(R.id.start6);
        Button start7 = findViewById(R.id.start7);
        Button start8 = findViewById(R.id.start8);
        Button start9 = findViewById(R.id.start9);

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 1;
                compute();
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 2;
                compute();
            }
        });
        start3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 3;
                compute();
            }
        });
        start4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 4;
                compute();
            }
        });
        start5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 5;
                compute();
            }
        });
        start6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 6;
                compute();
            }
        });
        start7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 7;
                compute();
            }
        });
        start8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 8;
                compute();
            }
        });
        start9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 9;
                compute();
            }
        });
    }
    private void compute(){
        switch (action){
            case 1:
                Intent intent1 = new Intent(getApplicationContext(),activity_bridge.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(getApplicationContext(),activity_chairsquat.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(getApplicationContext(),KneepushupActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(getApplicationContext(),StationaryLungeActivity.class);
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(getApplicationContext(),PtddActivity.class);
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(getApplicationContext(),StldkActivity.class);
                startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent(getApplicationContext(),BirddogActivity.class);
                startActivity(intent7);
                break;
            case 8:
                Intent intent8 = new Intent(getApplicationContext(),ForearmplankActivity.class);
                startActivity(intent8);
                break;
            case 9:
                Intent intent9 = new Intent(getApplicationContext(),WallsquatActivity.class);
                startActivity(intent9);
                break;
        }
    }
}

