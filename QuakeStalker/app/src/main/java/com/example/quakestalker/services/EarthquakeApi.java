package com.example.quakestalker.services;

import com.example.quakestalker.models.Quake;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthquakeApi {
    @GET("/fdsnws/event/1/query?format=geojson")
    Call<Quake> queryQuake();

    @GET("/fdsnws/event/1/query?format=geojson")
    Call<Quake> queryQuake(@Query("maxmagnitude") double maxMag,
                         @Query("minmagnitude") double minMag);

    @GET("/fdsnws/event/1/query?format=geojson")
    Call<Quake> queryQuake(@Query("starttime") String starttime,
                         @Query("endtime") String endtime,
                         @Query("limit") int limit);

    @GET("/fdsnws/event/1/query?format=geojson")
    Call<Quake> queryQuake(@Query("orderby") String orderby);
}
