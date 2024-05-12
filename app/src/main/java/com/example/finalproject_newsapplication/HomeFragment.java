package com.example.finalproject_newsapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {





    ListView listView;
    List<Article> articles;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        return view;

    }
    @Override
    public void onViewCreated(@NonNull View view,@NonNull Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
    RetrofitInstance.getInstance().newsAPI.getDataModel().enqueue(new Callback<DataModel>() {
        @Override
        public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                Log.d("API","onResponse"+response.body().toString());
                articles = response.body().getArticles();
                recyclerView.setAdapter(new NewsAdapter(requireActivity().getApplicationContext(), articles));

        }

        @Override
        public void onFailure(Call<DataModel> call, Throwable throwable) {
            Log.d("API","onFaliure"+"API not called");
        }
    });
    }
}