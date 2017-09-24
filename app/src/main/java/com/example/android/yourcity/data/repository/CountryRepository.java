package com.example.android.yourcity.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.android.yourcity.data.model.xml.Entry;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.data.remote.ApiXML;
import com.example.android.yourcity.ui.home.CallbackCountry;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CountryRepository {

    private final Context context;
    private final Api api;
    private final ApiXML apiXML;
    private List<String> countries;
    private List<String> cities;
    private String cityDescription;

    @Inject
    public CountryRepository(Context context, Api api, ApiXML apiXML) {
        this.api = api;
        this.apiXML = apiXML;
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

                    for (int i = 0; i < countriesJsonArray.length(); i++) {
                        JSONArray citiesJsonArray = jsonObject.getJSONArray((String) countriesJsonArray.get(i));
                        for (int j = 0; j < citiesJsonArray.length(); j++) {
                            contentValues.put(CityContract.CityEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                            contentValues.put(CityContract.CityEntry.COLUMN_CITY, citiesJsonArray.getString(j));
                            context.getContentResolver().insert(CityContract.CityEntry.CONTENT_URI, contentValues);
                        }
                        contentValues.put(CountryContract.CountryEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                        context.getContentResolver().insert(CountryContract.CountryEntry.CONTENT_URI,contentValues);
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


    public String getCityDescription(String selectedCityName){
        apiXML.getCityDescription(selectedCityName, 1, "demo").enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                cityDescription = response.body().getSummary();
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {

            }
        });
        return cityDescription;
    }
}
