package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DietPlan extends Fragment {
    RecyclerView recyclerView ;
    MyListAdapter myListAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<FoodCategoryList> foodCategoryListItem ;

    public DietPlan() {
        // Required empty public constructor
    }

    public static DietPlan newInstance(String param1, String param2) {
        DietPlan fragment = new DietPlan();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myListAdapter = new MyListAdapter(foodCategoryListItem);
        recyclerView.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
    }

    private void initData() {

        foodCategoryListItem = new ArrayList<FoodCategoryList>();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        String userID=firebaseAuth.getCurrentUser().getUid();

        db.collection("userData").document(userID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        foodCategoryListItem.add(new FoodCategoryList("Breakfast",user.getBreakfast_calories(),100));
                        foodCategoryListItem.add(new FoodCategoryList("Lunch",user.getLunch_calories(),100));
                        foodCategoryListItem.add(new FoodCategoryList("Evening Snacks",user.getEvening_snacks_calories(),100));
                        foodCategoryListItem.add(new FoodCategoryList("Dinner",user.getDinner_calories(),100));
                        myListAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet_plan, container, false);

        initData();
        initRecyclerView(view);
        return view;
    }
}