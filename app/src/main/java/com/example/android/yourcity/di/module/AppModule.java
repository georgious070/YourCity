package com.example.android.yourcity.di.module;

import android.content.Context;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.remote.ApiCityDescription;
import com.example.android.yourcity.data.remote.ApiGeonames;

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
    @Named("retrofit1")
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    ApiGeonames provideApi(@Named("retrofit1") Retrofit retrofit){
        return retrofit.create(ApiGeonames.class);
    }


    @Provides
    @Named("retrofit2")
    @Singleton
    Retrofit provideRetrofit2(){
        return new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiCityDescription provideApi2(@Named("retrofit2")Retrofit retrofitXML){
        return retrofitXML.create(ApiCityDescription.class);
    }
}
