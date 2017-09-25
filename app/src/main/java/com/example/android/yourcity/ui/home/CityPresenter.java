package com.example.android.yourcity.ui.home;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.yourcity.App;
import com.example.android.yourcity.busines.CountryInteractor;
import com.example.android.yourcity.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

@InjectViewState
public class CityPresenter extends BasePresenter<CityView> {

    private final CallbackCountry callbackCountry;

    @Inject
    CountryInteractor countryInteractor;

    public CityPresenter(CallbackCountry callbackCountry) {
        App.getApp().getComponent().inject(this);
        this.callbackCountry = callbackCountry;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        countryInteractor.loadData(callbackCountry);
    }

    void onSpinnerItemSelected(String selectedCountryName) {
        getViewState().showCities(countryInteractor.getCities(selectedCountryName));
    }
}
