package com.example.finalproject_newsapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryNews extends AppCompatActivity {
    RecyclerView categoryRecyclerView;
    List<Article> articles;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_news);

        String category = getIntent().getStringExtra("category");
        Log.d("category",category);
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryNews.this));

        RetrofitInstance.getInstance().newsAPI.categoryData(category,"en",NewsAPI.apiKey).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                Log.d("API","onResponse"+response.body().toString());
                articles = response.body().getArticles();
                categoryRecyclerView.setAdapter(new NewsAdapter(CategoryNews.this, articles));
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable throwable) {
                Log.d("API","onFaliure"+"API not called");
            }
        });
    }



}
