package com.example.android.yourcity;

import android.app.Application;

import com.example.android.yourcity.data.remote.Api;

import org.json.JSONObject;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static Api api;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Api.class);
    }

    public static Api getApi() {
        return api;
    }
}
