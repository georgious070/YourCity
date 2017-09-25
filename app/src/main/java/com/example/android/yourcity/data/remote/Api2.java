package com.example.android.yourcity.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;

@Api
public interface Api2 {
    @GET("countriesToCities.json")
    Call<Object> getData();
}
