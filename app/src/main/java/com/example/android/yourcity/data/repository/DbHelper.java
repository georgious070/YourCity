package com.example.android.yourcity.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "yourcity.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_COUNTRIES_TABLE = "CREATE TABLE " + CountryContract.CountryEntry.TABLE_NAME
                + "(" + CountryContract.CountryEntry.COLUMN_COUNTRY + " TEXT PRIMARY KEY);";
        sqLiteDatabase.execSQL(SQL_CREATE_COUNTRIES_TABLE);

        String SQL_CREATE_CITIES_TABLE = "CREATE TABLE " + CityContract.CityEntry.TABLE_NAME +
                "(" + CityContract.CityEntry.COLUMN_COUNTRY + " TEXT," +
                CityContract.CityEntry.COLUMN_CITY + " TEXT PRIMARY KEY);";
        sqLiteDatabase.execSQL(SQL_CREATE_CITIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
