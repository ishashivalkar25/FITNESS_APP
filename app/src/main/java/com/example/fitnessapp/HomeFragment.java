package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnessapp.DaysActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.calorie_tracker_page;
import com.example.fitnessapp.databinding.FragmentHomeBinding;
import com.example.fitnessapp.ui.home.HomeViewModel;
import com.example.fitnessapp.water_tracker;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageButton workout = root.findViewById(R.id.workout);
        ImageButton calorie_tracker = root.findViewById(R.id.calorie_tracker);
        ImageButton water_tracker_btn = root.findViewById(R.id.water_tracker_btn);

        water_tracker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), water_tracker.class));
            }
        });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(root.getContext(), DaysActivity.class));
            }
        });
        calorie_tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(root.getContext(), calorie_tracker_page.class));
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}