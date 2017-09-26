package com.example.android.yourcity.di.module;

import android.content.Context;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.remote.ApiCityDescription;
import com.example.android.yourcity.data.remote.ApiGeonames;
import com.example.android.yourcity.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

   private final Context context;
    public AppModule(App app) {
        context = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Named("retrofitGeonames")
    @Singleton
    Retrofit provideRetrofitGeonames() {
        return new Retrofit.Builder()
                .baseUrl(Constants.GEONAMES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    ApiGeonames provideApiGeonames(@Named("retrofitGeonames") Retrofit retrofit) {
        return retrofit.create(ApiGeonames.class);
    }


    @Provides
    @Named("retrofitDescription")
    @Singleton
    Retrofit provideRetrofitDescription() {
        return new Retrofit.Builder()
                .baseUrl(Constants.CITY_DESCRIPTION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiCityDescription provideApiDescription(@Named("retrofitDescription") Retrofit retrofit) {
        return retrofit.create(ApiCityDescription.class);
    }
}
