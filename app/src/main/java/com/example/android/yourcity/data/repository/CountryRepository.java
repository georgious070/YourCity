package com.example.android.yourcity.data.repository;

import android.util.Log;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.model.Country;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class CountryRepository {

    private List<String> countries;

    public CountryRepository() {
        countries = new ArrayList<>();
    }

    public List<String> getCountries() {
        App.getApi().getData().enqueue(new Callback<Country>() {

            @Override
            public void onResponse(Call<Country> call, retrofit2.Response<Country> response) {
                Country country = response.body();
                String value = new Gson().toJson(response.body());
                try {
                    JSONObject jsonObject = new JSONObject(value);
                    JSONArray val = jsonObject.names();
                    for (int i = 0; i < val.length(); i++) {
                        countries.add(val.get(i).toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                t.getMessage();
            }
        });
        return countries;
    }
}
