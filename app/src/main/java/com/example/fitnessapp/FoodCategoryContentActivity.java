package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class FoodCategoryContentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyFoodItemAdapter myFoodItemAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<FoodItem> foodItemsList;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    User user;
    ProgressDialog progressDialog;
    String food_category;
    long total_calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category_content);

        food_category = getIntent().getStringExtra("Food_Category");

        Log.i("food_category", food_category.toString());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data ....");
        progressDialog.show();

        foodItemsList = new ArrayList<FoodItem>();
        db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        String current_user = firebaseAuth.getCurrentUser().getUid();

        Log.i("Current User", current_user);
        db = FirebaseFirestore.getInstance();
        db.collection("userData").document(current_user).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                        TextView total_calories_counted_text = findViewById(R.id.total_calories_counted_text);
                        TextView total_calories_counted = findViewById(R.id.total_calories_counted);

                        if (food_category.equalsIgnoreCase("Breakfast")) {
                            total_calories_counted_text.setText("Total Calories Consumed ( Breakfast )");
                            total_calories_counted.setText(user.getBreakfast_calories() + "Cal");
                        } else if (food_category.equalsIgnoreCase("Lunch")) {
                            total_calories_counted_text.setText("Total Calories Consumed ( Lunch )");
                            total_calories_counted.setText(user.getLunch_calories() + "Cal");
                        } else if (food_category.equalsIgnoreCase("Evening Snacks")) {
                            total_calories_counted_text.setText("Total Calories Consumed ( Evening Snacks )");
                            total_calories_counted.setText(user.getEvening_snacks_calories() + "Cal");
                        } else {
                            total_calories_counted_text.setText("Total Calories Consumed ( Dinner )");
                            total_calories_counted.setText(user.getDinner_calories() + "Cal");
                        }

                    }
                });


        initRecyclerView();
        fetchData();

    }

    private void fetchData() {
        db.collection(food_category).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                foodItemsList.add(document.toObject(FoodItem.class));
                                myFoodItemAdapter.notifyDataSetChanged();
                            }
                        } else {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Log.w("Error", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewForFoodItems);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myFoodItemAdapter = new MyFoodItemAdapter(foodItemsList);
        recyclerView.setAdapter(myFoodItemAdapter);
        myFoodItemAdapter.notifyDataSetChanged();
    }
}