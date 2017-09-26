package com.example.android.yourcity.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.android.yourcity.data.database.CityContract;
import com.example.android.yourcity.data.database.CountryContract;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.data.remote.Api2;
import com.example.android.yourcity.ui.home.CallbackCountry;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {

    private final Context context;
    private final Api api;
    private final Api2 apiDesc;
    private List<String> countries;
    private List<String> cities;
    private String cityDescription;

    @Inject
    public CountryRepository(Context context, Api api, Api2 apiDesc) {
        this.api = api;
        this.apiDesc = apiDesc;
        countries = new ArrayList<>();
        cities = new ArrayList<>();
        this.context = context;
    }

    public void loadDataCountry(final CallbackCountry callback) {
        api.getData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray countriesJsonArray = jsonObject.names();

                    ContentValues contentValues = new ContentValues();
                    ContentValues contentValues2 = new ContentValues();

                    for (int i = 0; i < 5; i++) {
                        JSONArray citiesJsonArray = jsonObject.getJSONArray((String) countriesJsonArray.get(i));
                        for (int j = 0; j < 5; j++) {
                            contentValues2.put(CityContract.CityEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                            contentValues2.put(CityContract.CityEntry.COLUMN_CITY, citiesJsonArray.getString(j));
                            context.getContentResolver().insert(CityContract.CityEntry.CONTENT_URI, contentValues2);
                        }
                        contentValues2 = new ContentValues();
                        contentValues.put(CountryContract.CountryEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                        context.getContentResolver().insert(CountryContract.CountryEntry.CONTENT_URI, contentValues);
                        contentValues = new ContentValues();
                    }

                    String[] projection = {CountryContract.CountryEntry.COLUMN_COUNTRY};
                    Cursor cursorCountry = context.getContentResolver().query(CountryContract.CountryEntry.CONTENT_URI,
                            projection,
                            null,
                            null,
                            null, null);

                    for (int l = 0; l < cursorCountry.getCount(); l++) {
                        cursorCountry.moveToNext();
                        countries.add(cursorCountry
                                .getString(cursorCountry.getColumnIndex(CountryContract.CountryEntry.COLUMN_COUNTRY)));
                    }

                    callback.onResponse(countries);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    public List<String> getCities(String selectedCountryName) {
        String[] projection = {CityContract.CityEntry.COLUMN_CITY};
        String selection = CityContract.CityEntry.COLUMN_COUNTRY + " = ? ";
        String[] selectionArgs = {selectedCountryName};
        Cursor cursorCity = context.getContentResolver().query(CityContract.CityEntry.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null,
                null);
        for (int i = 0; i < cursorCity.getCount(); i++) {
            cursorCity.moveToNext();
            cities.add(cursorCity.getString(cursorCity.getColumnIndex(CityContract.CityEntry.COLUMN_CITY)));
        }
        return cities;
    }

    public String getCityDescription(String selectedCityName) {
        String lowerCase = selectedCityName.toLowerCase();
        byte[] encode = lowerCase.getBytes(StandardCharsets.UTF_8);
        apiDesc.getCityDescription(encode, 1, "demo").enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray jsonArray = jsonObject.names();
                    JSONObject jsonObject1 = jsonObject.getJSONObject((String) jsonArray.get(0));
                    JSONArray jsonArray1 = jsonObject1.names();
                    String message = jsonObject1.getString((String) jsonArray1.get(0));
                    cityDescription = message;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
        return cityDescription;
    }
}
