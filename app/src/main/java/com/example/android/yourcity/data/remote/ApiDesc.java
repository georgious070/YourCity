package com.example.android.yourcity.data.remote;


import com.example.android.yourcity.data.model.xml.example.Status;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Named;
import javax.inject.Qualifier;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//http://api.geonames.org/wikipediaSearch?q=london&maxRows=10&username=demo

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDesc {
}

