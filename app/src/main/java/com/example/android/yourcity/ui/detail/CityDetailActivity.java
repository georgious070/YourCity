package com.example.android.yourcity.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.android.yourcity.R;
import com.example.android.yourcity.ui.base.BaseActivity;
import com.example.android.yourcity.ui.home.CallbackCountry;

public class CityDetailActivity extends BaseActivity implements CityDetailView {

    private static final String KEY_CITY_NAME = "cityName";
    private TextView textCityDetail;

    private final CallbackCity callbackCity = new CallbackCity() {
        @Override
        public void onResponse(String cityDescription) {
            textCityDetail.setText(cityDescription);
        }
    };

    @InjectPresenter
    CityDetailPresenter cityDetailPresenter;

    @ProvidePresenter
    CityDetailPresenter providePresenter() {
        return new CityDetailPresenter(getIntent().getStringExtra(KEY_CITY_NAME), callbackCity);
    }

    public static Intent getIntent(Context context, String selectedCityName) {
        Intent intent = new Intent(context, CityDetailActivity.class);
        intent.putExtra(KEY_CITY_NAME, selectedCityName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        textCityDetail = (TextView) findViewById(R.id.text_city_detail);
    }
}
