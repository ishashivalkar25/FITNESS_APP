package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class calorie_tracker_page extends AppCompatActivity {

    private double progr;
    private long count=0;
    User user;
    ProgressBar progress_bar;
    TextView text_view_progress;
    TextView info;
    double male_calorie_intake_req=2500,female_calorie_intake_req=2000;
    long current_cal_intake=0;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_tracker_page);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        info = findViewById(R.id.info_cal);
        text_view_progress=findViewById(R.id.text_view_progress_cal);
        progress_bar = findViewById(R.id.progress_bar_cal);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.i("Calorie Tracker",userId+"");
        db.collection("userData").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        user =  documentSnapshot.toObject(User.class);
                        gender = user.getGender();
                        if(gender.equalsIgnoreCase("Male"))
                        {
                            current_cal_intake=user.getTotal_calories();
                            if(current_cal_intake<=male_calorie_intake_req)
                            {
                                progr = (current_cal_intake/male_calorie_intake_req)*100;
                            }
                            else
                            {
                                progr = 100;
                                Toast.makeText(calorie_tracker_page.this, "You have taken "+(current_cal_intake-male_calorie_intake_req)+" Cal more than your required calorie intake.", Toast.LENGTH_SHORT).show();
                            }
                            info.setText(current_cal_intake+" out of 2500 Cal");
                            updateProgressBar();
                        }
                        else
                        {
                            current_cal_intake=user.getTotal_calories();
                            if(current_cal_intake<=female_calorie_intake_req)
                            {
                                progr = (current_cal_intake/female_calorie_intake_req)*100;
                            }
                            else
                            {
                                progr = 100;
                                Toast.makeText(calorie_tracker_page.this, "You have taken "+(current_cal_intake-female_calorie_intake_req)+" Cal more than your required calorie intake.", Toast.LENGTH_SHORT).show();
                            }

                            info.setText(current_cal_intake+" out of 2000 Cal");
                            updateProgressBar();
                        }
                    }
                });


    }
    private void updateProgressBar(){
        progress_bar.setProgress((int) progr);
        text_view_progress.setText(String.format("%.2f",progr)+"%");

    }
}