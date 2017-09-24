package com.example.android.yourcity.data.repository;

import android.content.ContentResolver;
import android.net.Uri;

public class CityContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.mydb";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CITIES = "cities";

    public static class CityEntry {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CITIES);
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_CITY = "city";

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_CITIES;
    }
}
