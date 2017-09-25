package com.example.android.yourcity.di.module;

import android.content.Context;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.data.remote.Api2;
import com.example.android.yourcity.data.remote.ApiDesc;
import com.example.android.yourcity.data.remote.ApiDesc2;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

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
    @Api
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    Api2 provideApi(@Named("retrofit1") Retrofit retrofit){
        return retrofit.create(Api2.class);
    }


    @Provides
    @Singleton
    @ApiDesc
    Retrofit provideRetrofitXML(){
        return new Retrofit.Builder()
                .baseUrl("http://api.geonames.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiDesc2 provideApiXML(@Named("retrofit2")Retrofit retrofitXML){
        return retrofitXML.create(ApiDesc2.class);
    }
}
