package com.example.android.yourcity.data.remote;

import com.example.android.yourcity.data.model.xml.Entry;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//http://api.geonames.org/wikipediaSearch?q=london&maxRows=10&username=demo

public interface ApiXML {

    @GET("wikipediaSearch")
    Call<Entry> getCityDescription(@Query("q") byte[] query,
                                   @Query("maxRows") int maxRows,
                                   @Query("username") String username);
}
