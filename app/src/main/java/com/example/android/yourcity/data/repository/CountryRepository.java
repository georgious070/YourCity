package com.example.android.yourcity.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.android.yourcity.data.database.CityContract;
import com.example.android.yourcity.data.database.CountryContract;
import com.example.android.yourcity.data.remote.ApiCityDescription;
import com.example.android.yourcity.data.remote.ApiGeonames;
import com.example.android.yourcity.ui.detail.CallbackCity;
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

    private static final String WIKIPEDIA_USER_NAME = "demo";
    private static final String JSON_CITY_DESCRIPTION_KEY = "summary";
    private final Context context;
    private final ApiGeonames apiGeonames;
    private final ApiCityDescription apiDesc;
    private List<String> countries;
    private List<String> cities;
    private String cityDescription;

    @Inject
    public CountryRepository(Context context, ApiGeonames apiGeonames, ApiCityDescription apiCityDescription) {
        this.apiGeonames = apiGeonames;
        this.apiDesc = apiCityDescription;
        this.countries = new ArrayList<>();
        this.cities = new ArrayList<>();
        this.context = context;
    }

    public void loadDataCountry(final CallbackCountry callbackCountry) {
        apiGeonames.getData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray countriesJsonArray = jsonObject.names();

                    ContentValues contentValues = new ContentValues();
                    ContentValues contentValues2 = new ContentValues();

                    for (int i = 0; i < countriesJsonArray.length(); i++) {
                        JSONArray citiesJsonArray = jsonObject.getJSONArray((String) countriesJsonArray.get(i));
                        for (int j = 0; j < citiesJsonArray.length(); j++) {
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

                    callbackCountry.onSuccess(countries);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callbackCountry.onFailure(t.getMessage());
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

        if (cities != null) {
            cities.clear();
        }
        for (int i = 0; i < cursorCity.getCount(); i++) {
            cursorCity.moveToNext();
            cities.add(cursorCity.getString(cursorCity.getColumnIndex(CityContract.CityEntry.COLUMN_CITY)));
        }
        return cities;
    }

    public void loadCityDescription(CallbackCity callbackCityDescription, String selectedCityName) {
        String lowerCase = selectedCityName.toLowerCase();
        byte[] encode = lowerCase.getBytes(StandardCharsets.UTF_8);
        apiDesc.getCityDescription(encode, 1, WIKIPEDIA_USER_NAME).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                try {
                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                    JSONArray jsonArrayGeonames = jsonObject.names();
                    JSONArray jsonArrayGeonamesFirstArray = jsonObject.getJSONArray((String) jsonArrayGeonames.get(0));
                    JSONObject jsonObjectInsideFirstGeonames = jsonArrayGeonamesFirstArray.getJSONObject(0);
                    String message = jsonObjectInsideFirstGeonames.getString(JSON_CITY_DESCRIPTION_KEY);
                    cityDescription = message;
                    callbackCityDescription.onSuccess(cityDescription);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callbackCityDescription.onFailure("Daily limit of 30 000 credits has already been exceeded." +
                            " A lot of regards, Wikipedia");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callbackCityDescription.onFailure(t.getMessage());
            }
        });
    }
}
