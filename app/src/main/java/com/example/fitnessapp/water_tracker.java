package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java. util. Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class water_tracker extends AppCompatActivity {
    private long progr=0;
    private long count=0;
    User user;
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
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("userData").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        count=user.getNo_of_glasses_water_intake();
                        progr=count*10;
                        updateProgressBar();
                        info.setText(count +" out of 10 glasses");
                    }
                });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=user.getNo_of_glasses_water_intake();
                if(progr <=90) {
                    count += 1;
                    progr =count*10;
                    updateProgressBar();
                    info.setText(count +" out of 10 glasses");
                }
                else if(progr >= 100){
                    count ++;
                    info.setText(count +" out of 10 glasses");
                }
                user.no_of_glasses_water_intake=count;
                db.collection("userData").document(userId).set(user);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=user.getNo_of_glasses_water_intake();
                if(progr >=10 && count <=10) {
                    count -= 1;
                    progr =count*10;
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
                user.no_of_glasses_water_intake=count;
                db.collection("userData").document(userId).set(user);
            }
        });

    }
    private void updateProgressBar(){
        progress_bar.setProgress((int)progr);
        text_view_progress.setText(String.format("%.2f",progr)+"%");
        if(progr==100){
            Toast.makeText(water_tracker.this, "Completed your water goal today", Toast.LENGTH_SHORT).show();
        }
    }
}