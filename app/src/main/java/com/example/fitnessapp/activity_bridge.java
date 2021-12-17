package com.example.fitnessapp;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;


public class activity_bridge extends AppCompatActivity {

    private Chronometer chrono;
    private long pauseOffset;
    private boolean running;
    Button skip;
    FirebaseFirestore db;
    String userId ;
    User user;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        db.collection("userData").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                    }
                });

        skip=findViewById(R.id.skip1);
        chrono = findViewById(R.id.chronometer);
        chrono.setFormat("     %s");
        running = true;

        chrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsed >= 31000) {
                    user.setTotal_no_of_exercise(user.getTotal_no_of_exercise()+1);
                    db.collection("userData").document(userId).set(user);
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
