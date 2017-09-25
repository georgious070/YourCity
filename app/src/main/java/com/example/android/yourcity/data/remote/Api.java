package com.example.android.yourcity.data.remote;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Api {


}

