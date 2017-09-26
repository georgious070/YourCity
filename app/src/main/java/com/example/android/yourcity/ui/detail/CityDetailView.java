package com.example.android.yourcity.ui.detail;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.yourcity.ui.base.BaseView;

@StateStrategyType(AddToEndStrategy.class)
public interface CityDetailView extends BaseView {
    //no-op
}
