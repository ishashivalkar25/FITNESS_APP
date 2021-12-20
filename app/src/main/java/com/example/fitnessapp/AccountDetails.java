package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class AccountDetails extends Fragment {

    FirebaseFirestore db ;
    String userId;
    User user;
    TextInputEditText name ,email , age, weight,height,gender;
    Button save;
    public AccountDetails() {
        // Required empty public constructor
    }

    public static AccountDetails newInstance(String param1, String param2) {
        AccountDetails fragment = new AccountDetails();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);
        name = view.findViewById(R.id.name2);
        email = view.findViewById(R.id.email2);
        age = view.findViewById(R.id.age2);
        gender = view.findViewById(R.id.gender2);
        weight = view.findViewById(R.id.weight2);
        height = view.findViewById(R.id.height2);
        save=view.findViewById(R.id.save_details);
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        email.setEnabled(false);

        db.collection("userData").document(userId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        user= documentSnapshot.toObject(User.class);
                        name.setText(user.getName());
                        email.setText(user.getEmail());
                        age.setText(user.getAge()+"");
                        gender.setText(user.getGender());
                        weight.setText(user.getWeight()+"");
                        height.setText(user.getHeight()+"");
                    }
                });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(name.getText().toString().trim());
                user.setAge(Long.parseLong(age.getText().toString().trim()));
                user.setGender(gender.getText().toString().trim());
                user.setWeight(Long.parseLong(weight.getText().toString().trim()));
                user.setHeight(Long.parseLong(height.getText().toString().trim()));
                db.collection("userData").document(userId).set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull @NotNull Void unused) {
                                Toast.makeText(view.getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return view;
    }
}