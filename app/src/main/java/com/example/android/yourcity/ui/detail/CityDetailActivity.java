package com.example.android.yourcity.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.example.android.yourcity.R;
import com.example.android.yourcity.ui.base.BaseActivity;

public class CityDetailActivity extends BaseActivity implements CityDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
    }

    @Override
    public void showCityDescriptoin() {

    }
}
