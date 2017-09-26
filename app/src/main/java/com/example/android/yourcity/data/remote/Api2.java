package com.example.android.yourcity.data.remote;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//http://api.geonames.org/wikipediaSearch?q=london&maxRows=10&username=demo

public interface Api2 {

    @GET("wikipediaSearchJSON")
    Call<Object> getCityDescription(@Query("q") byte[] query,
                                    @Query("maxRows") int maxRows,
                                    @Query("username") String username);
}
