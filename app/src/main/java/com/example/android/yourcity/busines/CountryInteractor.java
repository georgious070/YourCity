package com.example.android.yourcity.busines;

import com.example.android.yourcity.data.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;

public class CountryInteractor {

    private CountryRepository countryRepository;

    public CountryInteractor(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public List<String> getCountries(){
        return countryRepository.getCountries();
    }
}
