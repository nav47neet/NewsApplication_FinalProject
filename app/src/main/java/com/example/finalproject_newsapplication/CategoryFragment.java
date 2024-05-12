package com.example.finalproject_newsapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class CategoryFragment extends Fragment {

    private String category;
    private Button techButton, politicsButton, healthButton, scienceButton, sportsButton, worldButton, businessButton, entertainmentButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeButtons(view);
        setupClickListeners();
    }

    private void initializeButtons(View view) {
        techButton = view.findViewById(R.id.technology);
        politicsButton = view.findViewById(R.id.politics);
        healthButton = view.findViewById(R.id.health);
        scienceButton = view.findViewById(R.id.science);
        sportsButton = view.findViewById(R.id.sports);
        worldButton = view.findViewById(R.id.world);
        businessButton = view.findViewById(R.id.business);
        entertainmentButton = view.findViewById(R.id.entertainment);
    }

    private void setupClickListeners() {
        techButton.setOnClickListener(v -> openRecyclerViewActivity(techButton.getText().toString().toLowerCase()));
        politicsButton.setOnClickListener(v -> openRecyclerViewActivity(politicsButton.getText().toString().toLowerCase()));
        healthButton.setOnClickListener(v -> openRecyclerViewActivity(healthButton.getText().toString().toLowerCase()));
        scienceButton.setOnClickListener(v -> openRecyclerViewActivity(scienceButton.getText().toString().toLowerCase()));
        sportsButton.setOnClickListener(v -> openRecyclerViewActivity(sportsButton.getText().toString().toLowerCase()));
        worldButton.setOnClickListener(v -> openRecyclerViewActivity(worldButton.getText().toString().toLowerCase()));
        businessButton.setOnClickListener(v -> openRecyclerViewActivity(businessButton.getText().toString().toLowerCase()));
        entertainmentButton.setOnClickListener(v -> openRecyclerViewActivity(entertainmentButton.getText().toString().toLowerCase()));
    }

    private void openRecyclerViewActivity(String category) {
        try {
            Log.e("intent", "startingIntent");
            Intent intent = new Intent(getActivity(), CategoryNews.class);
            intent.putExtra("category", category);
            startActivity(intent);
            Log.e("intent", "endingIntent");
        } catch (Exception e) {
            Log.e("intent", "Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
