package com.example.android.yourcity.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android.yourcity.R;
import com.example.android.yourcity.ui.base.BaseActivity;

public class CityDetailActivity extends BaseActivity implements CityDetailView {
    private static final String KEY_CITY_NAME = "cityName";

    @InjectPresenter
    CityDetailPresenter cityDetailPresenter;

    public static Intent getIntent(Context context, String selectedCityName){
        Intent intent = new Intent(context, CityDetailActivity.class);
        intent.putExtra(KEY_CITY_NAME, selectedCityName);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
    }

    @Override
    public void showCityDescriptoin() {

    }
}
