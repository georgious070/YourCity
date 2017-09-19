package com.example.android.yourcity.ui.home;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.yourcity.R;

import org.w3c.dom.Text;

import java.util.List;

public class CountrySpinnerAdapter extends ArrayAdapter {

    private List<String> countries;

    public CountrySpinnerAdapter(@NonNull Context context, int resource, List<String> countries) {
        super(context, resource, countries);
        this.countries = countries;
    }

    void setData(List<String> newCountry) {
        countries.clear();
        countries.addAll(newCountry);
        notifyDataSetChanged();
    }

    public View getCountrySpinnerView(int position, View convertView,
                                      ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent, false);
        final TextView countryText = view.findViewById(R.id.text_country_name);
        countryText.setText(countries.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCountrySpinnerView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCountrySpinnerView(position, convertView, parent);
    }


}
