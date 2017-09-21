package com.example.android.yourcity.ui.home;

import android.widget.AdapterView;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.yourcity.ui.base.BaseView;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CityView extends BaseView {

    void showCountries(List<String> countriesList);

    void showCities(List<String> citiesList);
}
