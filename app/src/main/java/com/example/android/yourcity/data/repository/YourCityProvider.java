package com.example.android.yourcity.data.repository;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class YourCityProvider extends ContentProvider {

    private DbHelper dbHelper;
    private static final int COUNTRIES = 100;
    private static final int CITIES = 101;
    SQLiteDatabase sqLiteDatabase;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(CountryContract.CONTENT_AUTHORITY, CountryContract.PATH_COUNTRIES, COUNTRIES);
        uriMatcher.addURI(CityContract.CONTENT_AUTHORITY, CityContract.PATH_CITIES, CITIES);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor;
        int match = uriMatcher.match(uri);
        switch (match) {
            case COUNTRIES:
                cursor = sqLiteDatabase.query(CountryContract.CountryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CITIES:
                cursor = sqLiteDatabase.query(CityContract.CityEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case COUNTRIES:
                return CountryContract.CountryEntry.CONTENT_LIST_TYPE;
            case CITIES:
                return CityContract.CityEntry.CONTENT_LIST_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {


        int match = uriMatcher.match(uri);
        long rawId;

        switch (match) {
            case COUNTRIES:
                rawId = sqLiteDatabase.insert(CountryContract.CountryEntry.TABLE_NAME, null, contentValues);
                break;
            case CITIES:
                rawId = sqLiteDatabase.insert(CityContract.CityEntry.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }

        Uri resultUri = ContentUris.withAppendedId(uri, rawId);
        getContext().getContentResolver().notifyChange(resultUri,null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}