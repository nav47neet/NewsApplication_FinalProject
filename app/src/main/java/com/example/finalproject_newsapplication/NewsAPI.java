package com.example.finalproject_newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    public static final String apiKey = "9e1c7a818f01397a3fa6b15eec72da63";
    @GET("top-headlines?country=in&lang=en&apikey="+apiKey)
    Call<DataModel> getDataModel();

    @GET("search")
    Call<DataModel> searchData(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
}
