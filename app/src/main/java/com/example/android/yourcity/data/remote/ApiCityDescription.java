package com.example.android.yourcity.data.remote;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCityDescription {

    @GET("wikipediaSearchJSON")
    Call<Object> getCityDescription(@Query("q") byte[] query,
                                    @Query("maxRows") int maxRows,
                                    @Query("username") String username);
}
