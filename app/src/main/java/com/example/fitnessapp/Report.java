package com.example.fitnessapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class Report extends Fragment {

    FirebaseFirestore db;
    User user;
    String userId="";
    TextView duration_text,exercise_text,calorie_present_text,height_text,weight_text,bmi_text;
    public Report() {
        // Required empty public constructor
    }

    public static Report newInstance(String param1, String param2) {
        Report fragment = new Report();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        db= FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        duration_text = view.findViewById(R.id.duration_text);
        exercise_text = view.findViewById(R.id.exercise_text);
        calorie_present_text = view.findViewById(R.id.calorie_present_text);
        height_text = view.findViewById(R.id.height_text);
        weight_text = view.findViewById(R.id.weight_text);
        bmi_text = view.findViewById(R.id.bmi_text);

        db.collection("userData").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        duration_text.setText((user.getTotal_no_of_exercise()*30)+" seconds");
                        exercise_text.setText(user.getTotal_no_of_exercise()+"");
                        calorie_present_text.setText((user.getTotal_no_of_exercise()*7)+" Cal");
                        height_text.setText(user.getHeight()+"");
                        weight_text.setText(user.getWeight()+"");
                        double bmi = ((double)user.getWeight()*10000)/(user.getHeight()*user.getHeight());
                        bmi_text.setText(bmi+"");
                    }
                });
        return view;
    }
}