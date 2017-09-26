package com.example.android.yourcity.busines;

import com.example.android.yourcity.data.repository.CountryRepository;
import com.example.android.yourcity.ui.detail.CallbackCity;
import com.example.android.yourcity.ui.home.CallbackCountry;

import java.util.List;

import javax.inject.Inject;

public class CountryInteractor {

    private CountryRepository countryRepository;

    @Inject
    public CountryInteractor(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void loadData(CallbackCountry callbackCountry) {
        countryRepository.loadDataCountry(callbackCountry);
    }

    public List<String> getCities(String selectedCountryName){
        return countryRepository.getCities(selectedCountryName);
    }

    public void loadCityDescription(CallbackCity callbackCity, String selectedCityName){
        countryRepository.loadCityDescription(callbackCity, selectedCityName );
    }
}
