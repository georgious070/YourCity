package com.example.android.yourcity.data.remote;

import com.example.android.yourcity.data.model.Country;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("countriesToCities.json")
    Call<Country> getData();
}
