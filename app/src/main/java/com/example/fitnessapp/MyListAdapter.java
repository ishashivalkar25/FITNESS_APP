package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.foodListHolder> {

    private ArrayList<FoodCategoryList> foodCategoryListItem ;
    private ImageView add_from_category;
    private Context mContext;

    public MyListAdapter(ArrayList<FoodCategoryList> foodCategoryListItem) {
        this.foodCategoryListItem = foodCategoryListItem;
    }

    @NonNull
    @NotNull
    @Override
    public MyListAdapter.foodListHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_category_design,parent,false);
        return new foodListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MyListAdapter.foodListHolder holder, int position) {

        String food_category= foodCategoryListItem.get(position).getFood_category();
        float total_cal= foodCategoryListItem.get(position).getTotal_cal();
        float calculated_cal= foodCategoryListItem.get(position).getTotal_cal();
        holder.setData(food_category,calculated_cal,total_cal);

        add_from_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report fragment = new Report();

                Intent intent = new Intent(mContext,FoodCategoryContentActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodCategoryListItem.size();
    }

    class foodListHolder extends RecyclerView.ViewHolder {

        private TextView food_category_text ;
        private TextView cal_text;

        public foodListHolder(View view) {
            super(view);
            food_category_text=view.findViewById(R.id.Food_category_name);
            cal_text=view.findViewById(R.id.calories_cal);
            add_from_category=view.findViewById(R.id.add_items_of_category);
            mContext=view.getContext();

        }

        public void setData(String food_category, float calculated_cal, float total_cal) {
            food_category_text.setText(food_category);
            cal_text.setText(calculated_cal + " of " +  total_cal +" cal");
        }
    }

}


