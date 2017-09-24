package com.example.android.yourcity.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.model.Country;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.ui.home.CityActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class CountryRepository {

    private final Api api;
    private List<String> countries;

    @Inject
    public CountryRepository(Api api) {
        this.api = api;
        countries = new ArrayList<>();
    }

    public List<String> getCountries() {
        api.getData().enqueue(new Callback<Country>() {

            @Override
            public void onResponse(Call<Country> call, retrofit2.Response<Country> response) {
                Context context = App.getApp().getApplicationContext();
                String jsonResponse = new Gson().toJson(response.body());

                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray countriesNames = jsonObject.names();

                    ContentValues contentValues = new ContentValues();

                    List<String> citiesNames = new ArrayList<String>();

                    for (int i = 0; i < countriesNames.length(); i++) {
                        JSONArray citiesJsonArray = jsonObject.getJSONArray((String) countriesNames.get(i));
                        for(int j = 0; j < citiesJsonArray.length(); j++){
                            citiesNames.add(citiesJsonArray.getString(j));

                        }
                        countries.add(countriesNames.get(i).toString());
                        contentValues.put(CountryContract.CountryEntry.COLUMN_COUNTRY, countries.get(i));
                        Uri uri = context.getContentResolver().insert(CountryContract.CountryEntry.CONTENT_URI, contentValues);
                    }

//                    List<String> cities = new ArrayList<String>();
//                    String[] projection = {CountryContract.CountryEntry.COLUMN_COUNTRY};
//                    Cursor cursorCountry = context.getContentResolver().query(CountryContract.CountryEntry.CONTENT_URI,
//                            projection,
//                            null,
//                            null,
//                            null,
//                            null);
//                    for (int l = 0; l < cursorCountry.getCount(); l++) {
//                        cursorCountry.moveToNext();
//                        cities.add(cursorCountry.getString(cursorCountry.getColumnIndex(CountryContract.CountryEntry.COLUMN_COUNTRY)));
//                    }


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
