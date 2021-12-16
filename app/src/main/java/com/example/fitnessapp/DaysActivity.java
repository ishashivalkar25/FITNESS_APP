package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DaysActivity extends AppCompatActivity {
    int action=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        Button day1 = (Button)findViewById(R.id.day1);
        Button day2 = (Button)findViewById(R.id.day2);
        Button day3 = (Button)findViewById(R.id.day3);
        Button day4 = (Button)findViewById(R.id.day4);
        Button day5 = (Button)findViewById(R.id.day5);
        Button day6 = (Button)findViewById(R.id.day6);
        Button day7 = (Button)findViewById(R.id.day7);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 1;
                compute();
                finish();
            }
        });
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 2;
                compute();
                finish();
            }
        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 3;
                compute();
                finish();
            }
        });
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 4;
                compute();
                finish();
            }
        });
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 5;
                compute();
                finish();
            }
        });
        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 6;
                compute();
                finish();
            }
        });
        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action = 7;
                compute();
                finish();
            }
        });
    }

    private void compute(){
        switch (action){
            case 1:
                Intent intent1 = new Intent(getApplicationContext(),Day1Activity.class);
                startActivity(intent1);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(getApplicationContext(),Day2Activity.class);
                startActivity(intent2);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(getApplicationContext(),Day3Activity.class);
                startActivity(intent3);
                finish();
                break;
            case 4:
                Intent intent4 = new Intent(getApplicationContext(),RestdayActivity.class);
                startActivity(intent4);
                finish();
                break;
            case 5:
                Intent intent5 = new Intent(getApplicationContext(),WorkoutActivity.class);
                startActivity(intent5);
                finish();
                break;
            case 6:
                Intent intent6 = new Intent(getApplicationContext(),Day6Activity.class);
                startActivity(intent6);
                finish();
                break;
            case 7:
                Intent intent7 = new Intent(getApplicationContext(),Day7Activity.class);
                startActivity(intent7);
                finish();
                break;
        }


    }
}