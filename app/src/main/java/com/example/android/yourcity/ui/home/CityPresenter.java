package com.example.android.yourcity.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.example.android.yourcity.App;
import com.example.android.yourcity.busines.CountryInteractor;
import com.example.android.yourcity.data.repository.CountryRepository;
import com.example.android.yourcity.ui.base.BasePresenter;

import javax.inject.Inject;

@InjectViewState
public class CityPresenter extends BasePresenter<CityView> {

    @Inject
    CountryInteractor countryInteractor;

    public CityPresenter() {
        App.getApp().getComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
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
