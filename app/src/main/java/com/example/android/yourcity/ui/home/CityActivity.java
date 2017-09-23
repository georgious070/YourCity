package com.example.android.yourcity.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.yourcity.R;
import com.example.android.yourcity.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends BaseActivity implements CityView,
        CitiesAdapter.OnCityClickListener {

    private final CallbackCountry callbackCountry = new CallbackCountry() {
        @Override
        public void onResponse(List<String> countries) {
            countrySpinnerAdapter.setData(countries);
        }
    };

    @InjectPresenter
    CityPresenter cityPresenter;
    private RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;
    private Spinner spinner;
    private CountrySpinnerAdapter countrySpinnerAdapter;

    @ProvidePresenter
    CityPresenter providePresenter() {
        return new CityPresenter(callbackCountry);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        countrySpinnerAdapter = new CountrySpinnerAdapter(this, R.layout.spinner_item, new ArrayList<String>());
        spinner.setAdapter(countrySpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityPresenter.onSpinnerItemSelected(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cities);
        citiesAdapter = new CitiesAdapter(new ArrayList<String>(), this);
        recyclerView.setAdapter(citiesAdapter);
    }


    @Override
    public void onClickCity(String cityName) {
        //TODO from CityActivity -> CityDetailActivity
    }

    @Override
    public void showCities(List<String> cities) {
        citiesAdapter.setData(cities);
    }
}
