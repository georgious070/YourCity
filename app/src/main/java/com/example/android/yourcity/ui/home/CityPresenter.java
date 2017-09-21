package com.example.android.yourcity.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.yourcity.busines.CountryInteractor;
import com.example.android.yourcity.data.repository.CountryRepository;
import com.example.android.yourcity.ui.base.BasePresenter;

@InjectViewState
public class CityPresenter extends BasePresenter<CityView> {

    private CountryInteractor countryInteractor;

    public CityPresenter() {
        countryInteractor = new CountryInteractor(new CountryRepository());
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        getViewState().showCountries(countryInteractor.getCountries());
    }

//    private AdapterView.OnItemSelectedListener spinnerSelectedItem() {
//        return new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(adapterView.getContext(), " hello ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        };
//    }
}
