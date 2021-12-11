package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FoodCategoryContentActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    MyFoodItemAdapter myFoodItemAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<FoodItem> foodItemsList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category_content);
        initData();
        initRecyclerView();
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

    private void initData() {
        foodItemsList = new ArrayList<FoodItem>();
        foodItemsList.add(new FoodItem("Idli","1.0 Serve",100));
        foodItemsList.add(new FoodItem("Idli","1.0 Serve",100));
        foodItemsList.add(new FoodItem("Idli","1.0 Serve",100));
        foodItemsList.add(new FoodItem("Idli","1.0 Serve",100));
    }
}