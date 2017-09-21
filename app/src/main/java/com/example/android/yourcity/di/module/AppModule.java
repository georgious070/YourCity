package com.example.android.yourcity.di.module;

import android.content.Context;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.remote.Api;

import java.lang.annotation.RetentionPolicy;

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
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
}
