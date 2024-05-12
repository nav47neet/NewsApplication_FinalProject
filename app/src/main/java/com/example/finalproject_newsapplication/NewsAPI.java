package com.example.finalproject_newsapplication;

import android.provider.ContactsContract;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    public static final String apiKey = "14b35e261c6808ccd7d15354c0f153be";//"9e1c7a818f01397a3fa6b15eec72da63";360b65b4d11ffff15b28ed21af2b7b64
    @GET("top-headlines?country=in&lang=en&apikey="+apiKey)
    Call<DataModel> getDataModel();

    @GET("search")
    Call<DataModel> searchData(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
    @GET("top-headlines?")
    Call<DataModel> categoryData(@Query("category") String category,
                                 @Query("lang") String lang,
                                 @Query("apikey")String apiKey);

}
