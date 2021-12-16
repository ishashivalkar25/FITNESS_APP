package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CompletionActivity extends AppCompatActivity {
    Button gotohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion);
        gotohome = findViewById(R.id.button2);
        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(),DaysActivity.class);
                 startActivity(intent);
                 finish();
              //  getSupportFragmentManager().beginTransaction().replace(, new HomeFragment());
            }
        });
    }
}