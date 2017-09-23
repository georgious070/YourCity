package com.example.android.yourcity.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.model.Country;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.ui.home.CallbackCountry;
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

import static com.example.android.yourcity.data.repository.CountryContract.CountryEntry.TABLE_NAME;

public class CountryRepository {

    private final Context context;
    private final Api api;
    private List<String> countries;
    private List<String> cities;
    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Inject
    public CountryRepository(Context context, Api api) {
        this.api = api;
        countries = new ArrayList<>();
        cities = new ArrayList<>();
        this.context = context;
    }

    public void loadDataCountry(final CallbackCountry callback) {
        api.getData().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, retrofit2.Response<Object> response) {
                String jsonResponse = new Gson().toJson(response.body());

                try {
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray countriesJsonArray = jsonObject.names();

                    dbHelper = new DbHelper(context);
                    sqLiteDatabase = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();

                    for (int i = 0; i < countriesJsonArray.length(); i++) {
                        JSONArray citiesJsonArray = jsonObject.getJSONArray((String) countriesJsonArray.get(i));
                        for (int j = 0; j < citiesJsonArray.length(); j++) {
                            contentValues.put(CityContract.CityEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                            contentValues.put(CityContract.CityEntry.COLUMN_CITY, citiesJsonArray.getString(j));
                            sqLiteDatabase.insert(CityContract.CityEntry.TABLE_NAME, null, contentValues);

                        }
                        contentValues.put(CountryContract.CountryEntry.COLUMN_COUNTRY, (String) countriesJsonArray.get(i));
                        sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, contentValues);
                    }

                    dbHelper.close();

                    String[] projection = {CountryContract.CountryEntry.COLUMN_COUNTRY};
                    Cursor cursorCountry = sqLiteDatabase.query(CountryContract.CountryEntry.TABLE_NAME,
                            projection,
                            null,
                            null,
                            null, null, null);
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
        Cursor cursorCity = sqLiteDatabase.query(CityContract.CityEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
        for (int i = 0; i < cursorCity.getCount(); i++) {
            cursorCity.moveToNext();
            cities.add(cursorCity.getString(cursorCity.getColumnIndex(CityContract.CityEntry.COLUMN_CITY)));
        }
        return cities;
    }
}
