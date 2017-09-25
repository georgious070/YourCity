package com.example.android.yourcity.busines;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.model.json.Country;
import com.example.android.yourcity.data.repository.CountryRepository;
import com.example.android.yourcity.ui.home.CallbackCountry;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class CountryInteractor {

    private CountryRepository countryRepository;

    @Inject
    public CountryInteractor(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        App.getApp().getComponent().inject(this);
    }

    public void loadData(CallbackCountry callbackCountry) {
        countryRepository.loadDataCountry(callbackCountry);
    }

    public List<String> getCities(String selectedCountryName){
        return countryRepository.getCities(selectedCountryName);
    }

    public String getCityDescription(String selectedCityName){
        return countryRepository.getCityDescription(selectedCityName);
    }
}
