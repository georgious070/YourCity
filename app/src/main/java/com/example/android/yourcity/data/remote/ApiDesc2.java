package com.example.android.yourcity.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

@ApiDesc
public interface ApiDesc2{
    @GET("wikipediaSearchJSON")
    Call<Object> getCityDescription(@Query("q") byte[] query,
                                    @Query("maxRows") int maxRows,
                                    @Query("username") String username);
}
