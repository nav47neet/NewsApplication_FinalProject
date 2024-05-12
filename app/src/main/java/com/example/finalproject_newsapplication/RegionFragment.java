package com.example.finalproject_newsapplication;

import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegionFragment extends Fragment implements LocationListener {

    LocationManager locationManager;

    String cityName;
    RecyclerView regionRecyclerView;
    List<Article> articles;
    boolean isCityNameAvailable = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_region, container, false);


        grantPermission();
        checkLocationIsEnabledOrNot();
        getLocation();

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regionRecyclerView = view.findViewById(R.id.regionRecyclerView);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext()));
        if (isCityNameAvailable) {
            makeAPICall();
        }

    }

    private void makeAPICall() {
        RetrofitInstance.getInstance().newsAPI.searchData(cityName, NewsAPI.apiKey).enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API", "onResponse: Success");
                    articles = response.body().getArticles();
                    regionRecyclerView.setAdapter(new NewsAdapter(requireActivity().getApplicationContext(), articles));
                } else {
                    Log.d("API", "onResponse: Response unsuccessful or body is null");
                    // Handle unsuccessful response or null response body (e.g., show error message)
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable throwable) {
                Log.d("API", "onFailure" + "API not called");
            }
        });
    }


    private void checkLocationIsEnabledOrNot() {
        LocationManager lm = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;
        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gpsEnabled && !networkEnabled) {
            new AlertDialog.Builder(requireActivity()).setTitle("Enable Location Service").setCancelable(false).setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("Cancel", null).show();
        }
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 50000, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();

        }
    }

    private void grantPermission() {
        if (ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            cityName = addresses.get(0).getAdminArea();
            isCityNameAvailable = true;
            makeAPICall();
            Log.d("city", cityName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}