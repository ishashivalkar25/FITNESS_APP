package com.example.fitnessapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnessapp.databinding.FragmentHomeBinding;

public class MentalWellness extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_mental_wellness, container, false);
        TextView tip1 = view.findViewById(R.id.tip1);
        TextView tip2 = view.findViewById(R.id.tip2);
        TextView tip3 = view.findViewById(R.id.tip3);
        TextView tip4 = view.findViewById(R.id.tip4);
        TextView tip5 = view.findViewById(R.id.tips5);
        TextView tip6 = view.findViewById(R.id.tip6);

        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/8az-gfljEbg";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/_emoxlq5Dh4";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/nhkcv0jnCt4";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/qa7zGZmiLNk";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/YvqeWcPwd2o";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        tip6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://youtu.be/vVsXO9brK7M";
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        return view;
    }


}
