package com.example.fitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyFoodItemAdapter extends RecyclerView.Adapter<MyFoodItemAdapter.ItemHolder> {

    private ArrayList<FoodItem> foodItemsList;

    public MyFoodItemAdapter(ArrayList<FoodItem> foodItemsList) {
        this.foodItemsList = foodItemsList;
    }

    @NonNull
    @NotNull
    @Override
    public MyFoodItemAdapter.ItemHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_design,parent,false);
        return new MyFoodItemAdapter.ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyFoodItemAdapter.ItemHolder holder, int position) {

        String item_name = foodItemsList.get(position).getName();
        String item_description = foodItemsList.get(position).getDescription();
        float item_calorie_present = foodItemsList.get(position).getCal_present();
        holder.setData(item_name,item_description,item_calorie_present);
    }

    @Override
    public int getItemCount() {
        return foodItemsList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        TextView IName, IDescription, ICalPresent;

        public ItemHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            IName = itemView.findViewById(R.id.item_name);
            IDescription = itemView.findViewById(R.id.item_description);
            ICalPresent = itemView.findViewById(R.id.calorie_present);
        }

        public void setData(String item_name, String item_description, float item_calorie_present) {
            IName.setText(item_name);
            IDescription.setText(item_description);
            ICalPresent.setText(item_calorie_present+" cal");
        }
    }
}
