package com.example.android.yourcity;

import android.app.Application;

import com.example.android.yourcity.di.component.AppComponent;
import com.example.android.yourcity.di.component.DaggerAppComponent;
import com.example.android.yourcity.di.module.AppModule;

public class App extends Application {

    private AppComponent appComponent;
    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
