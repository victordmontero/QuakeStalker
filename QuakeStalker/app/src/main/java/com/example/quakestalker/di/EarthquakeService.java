package com.example.quakestalker.di;

import android.util.Log;

import com.example.quakestalker.models.Feature;
import com.example.quakestalker.models.Quake;
import com.example.quakestalker.services.EarthquakeApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthquakeService {

    public final String BASE_URL = "https://earthquake.usgs.gov/";

    Retrofit retrofit;

    @Inject
    public EarthquakeService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public List<Feature> queryEarthquakes() {
        Call<Quake> result;
        EarthquakeApi api = retrofit.create(EarthquakeApi.class);

        try {
            result = api.queryQuake();
            return result.execute().body().getFeatures();
        } catch (Exception ex) {
            Log.e("EarthquakeService",ex.getMessage());
        } finally {
            return null;
        }
    }
}
