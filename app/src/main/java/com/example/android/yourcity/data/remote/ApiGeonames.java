package com.example.android.yourcity.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiGeonames {

    @GET("countriesToCities.json")
    Call<Object> getData();
}
