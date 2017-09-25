package com.example.android.yourcity.ui.detail;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.yourcity.App;
import com.example.android.yourcity.busines.CountryInteractor;
import com.example.android.yourcity.ui.base.BasePresenter;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;

@InjectViewState
public class CityDetailPresenter extends BasePresenter<CityDetailView> {

    @Inject
    CountryInteractor countryInteractor;

    private String selectedCityName;

    public CityDetailPresenter(String selectedCityName) {
        App.getApp().getComponent().inject(this);
        this.selectedCityName = selectedCityName;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showCityDescriptoin(countryInteractor.getCityDescription(selectedCityName));
    }
}
