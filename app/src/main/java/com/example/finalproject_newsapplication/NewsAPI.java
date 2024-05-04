package com.example.finalproject_newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsAPI {
    public static final String apiKey = "524cfe1ac8cd4abc886d4c8f5bb899cc";
    @GET("top-headlines?q=tesla&from=2024-04-04&sortBy=publishedAt&apiKey="+apiKey)
    Call<DataModel> getDataModel();
}
