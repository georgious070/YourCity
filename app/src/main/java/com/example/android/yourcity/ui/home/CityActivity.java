package com.example.android.yourcity.ui.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android.yourcity.R;
import com.example.android.yourcity.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends BaseActivity implements CityView, CitiesAdapter.OnCityClickListener {

    @InjectPresenter
    CityPresenter cityPresenter;

    private RecyclerView recyclerView;
    private CitiesAdapter citiesAdapter;
    private Spinner spinner;
    private CountrySpinnerAdapter countrySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cities);
        citiesAdapter = new CitiesAdapter(new ArrayList<String>(), this);
        recyclerView.setAdapter(citiesAdapter);
    }

    @Override
    public void showCountries(List<String> countriesList) {
        spinner = (Spinner) findViewById(R.id.spinner);
        countrySpinnerAdapter = new CountrySpinnerAdapter(this, R.layout.spinner_item, new ArrayList<String>());
        spinner.setAdapter(countrySpinnerAdapter);
        countrySpinnerAdapter.setData(countriesList);
    }

    @Override
    public void showCities(List<String> citiesList) {
        citiesAdapter.setData(citiesList);
    }

    @Override
    public void onClickCity(String cityName) {
        //TODO from CityActivity -> CityDetailActivity
    }
}
