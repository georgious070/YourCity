package com.example.android.yourcity.di.component;

import com.example.android.yourcity.App;
import com.example.android.yourcity.di.module.AppModule;
import com.example.android.yourcity.ui.home.CityPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(App app);

    void inject(CityPresenter cityPresenter);
}
