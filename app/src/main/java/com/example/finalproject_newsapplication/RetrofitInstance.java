package com.example.finalproject_newsapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static RetrofitInstance instance;
    NewsAPI newsAPI;
    String baseUrl = "https://gnews.io/api/v4/";

    RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        newsAPI = retrofit.create(NewsAPI.class);
    }

    public static RetrofitInstance getInstance(){
        if(instance==null){
            instance = new RetrofitInstance();
        }
        return instance;
    }
}
