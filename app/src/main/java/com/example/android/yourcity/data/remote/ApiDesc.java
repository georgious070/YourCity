package com.example.android.yourcity.data.remote;


import com.example.android.yourcity.data.model.xml.example.Status;

import javax.inject.Named;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//http://api.geonames.org/wikipediaSearch?q=london&maxRows=10&username=demo

public interface ApiDesc {

    @GET("wikipediaSearchJSON")
    Call<Object> getCityDescription(@Query("q") byte[] query,
                                    @Query("maxRows") int maxRows,
                                    @Query("username") String username);
}
