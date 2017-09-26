package com.example.android.yourcity.ui.detail;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.yourcity.App;
import com.example.android.yourcity.busines.CountryInteractor;
import com.example.android.yourcity.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.security.auth.callback.Callback;

@InjectViewState
public class CityDetailPresenter extends BasePresenter<CityDetailView> {

    private final CallbackCity callbackCity;
    @Inject
    CountryInteractor countryInteractor;
    private String selectedCityName;

    public CityDetailPresenter(String selectedCityName, CallbackCity callbackCity) {
        App.getApp().getComponent().inject(this);
        this.selectedCityName = selectedCityName;
        this.callbackCity = callbackCity;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        countryInteractor.loadCityDescription(callbackCity, selectedCityName);
    }
}
