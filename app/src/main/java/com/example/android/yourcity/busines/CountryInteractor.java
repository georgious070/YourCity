package com.example.android.yourcity.busines;

import com.example.android.yourcity.App;
import com.example.android.yourcity.data.remote.Api;
import com.example.android.yourcity.data.repository.CountryRepository;
import com.example.android.yourcity.ui.home.CallbackCountry;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

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

    public String getCityDescription(String selectedCityName){
        return countryRepository.getCityDescription(selectedCityName);
    }
}
