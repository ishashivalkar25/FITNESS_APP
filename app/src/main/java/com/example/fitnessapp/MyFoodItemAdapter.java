package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyFoodItemAdapter extends RecyclerView.Adapter<MyFoodItemAdapter.ItemHolder> {

    private ArrayList<FoodItem> foodItemsList;
    private long total_calories=0;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    String userID;
    User user;
    Context context;
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
        long item_calorie_present = foodItemsList.get(position).getCal_present();
        holder.setData(item_name,item_description,item_calorie_present);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        userID=firebaseAuth.getCurrentUser().getUid();
        db.collection("userData").document(userID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        user = documentSnapshot.toObject(User.class);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return foodItemsList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder{

        TextView IName, IDescription, ICalPresent,total_calorie_present;
        ImageView add_item_in_count,delete_item_from_count;

        public ItemHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            IName = itemView.findViewById(R.id.item_name);
            IDescription = itemView.findViewById(R.id.item_description);
            ICalPresent = itemView.findViewById(R.id.calorie_present);
            add_item_in_count=itemView.findViewById(R.id.add_item_in_count);
            delete_item_from_count=itemView.findViewById(R.id.delete_item_from_count);
            context=itemView.getContext();
            add_item_in_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   total_calories=user.getTotal_calories();
                   total_calories += Long.parseLong(ICalPresent.getText().toString());
                    user.setTotal_calories(total_calories);
                    db.collection("userData").document(userID).set(user);
                    Toast.makeText(context, "Item added successfully!!", Toast.LENGTH_SHORT).show();
                }
            });
            delete_item_from_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    total_calories=user.getTotal_calories();
                    total_calories -= Long.parseLong(ICalPresent.getText().toString());
                    user.setTotal_calories(total_calories);
                    db.collection("userData").document(userID).set(user);
                    Toast.makeText(context, "Item removed successfully!!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(String item_name, String item_description, long item_calorie_present) {
            IName.setText(item_name);
            IDescription.setText(item_description);
            ICalPresent.setText(item_calorie_present+"");
            //Log.i("data123",item_name+" "+item_description+" "+item_calorie_present);
        }

    }
}
