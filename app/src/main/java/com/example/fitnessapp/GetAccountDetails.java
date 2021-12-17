package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetAccountDetails extends AppCompatActivity {

    private EditText userName,userPassword, userEmail,age,gender,height,weight;
    private Button save_profile_details;
    String email="",password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_account_details);
        // Create a new user with a first and last name

        email = getIntent().getStringExtra("Email");
        password = getIntent().getStringExtra("Password");

        userName=findViewById(R.id.etUserName);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        save_profile_details = findViewById(R.id.save_profile_details);

        save_profile_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db= FirebaseFirestore.getInstance();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String user_name=userName.getText().toString().trim();
                long user_age = Long.parseLong(age.getText().toString().trim());
                String user_gender=gender.getText().toString().trim();
                long user_height=Long.parseLong(height.getText().toString().trim());
                long user_weight=Long.parseLong(weight.getText().toString().trim());

                User user = new User();
                user.setFlag(false);
                user.setName(user_name);
                user.setAge(user_age);
                user.setGender(user_gender);
                user.setHeight(user_height);
                user.setWeight(user_weight);
                long bmi = (long)(user_weight/(user_height*user_height*100.0));
                user.setBmi(bmi);
                user.setBreakfast_calories(0);
                user.setLunch_calories(0);
                user.setEvening_snacks_calories(0);
                user.setDinner_calories(0);
                user.setExercises_done(new ArrayList<String>());
                user.setNo_of_glasses_water_intake(0);
                user.setTotal_duration_of_exercises(0);
                user.setTotal_no_of_exercise(0);
                user.setPassword(password);
                user.setEmail(email);
                user.setTotal_calories_burned(0);
                user.setTotal_calories_burned(0);

                db.collection("userData").document(userID)
                        .set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                Log.i("InsertedSuc","Success");
                                startActivity(new Intent(GetAccountDetails.this, profile_activity.class));

                            }
                        });

            }
        });


    }
}