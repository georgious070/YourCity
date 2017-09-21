package com.example.android.yourcity.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.yourcity.R;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {

    private List<String> cities;
    OnCityClickListener listener;

    public CitiesAdapter(List<String> cities, OnCityClickListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    void setData(List<String> newCities) {
        cities.clear();
        cities.addAll(newCities);
        notifyDataSetChanged();
    }

    public interface OnCityClickListener {
        void onClickCity(String cityName);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.viewCity.setText(cities.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickCity(holder.viewCity.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewCity;

        public ViewHolder(View itemView) {
            super(itemView);
            viewCity = itemView.findViewById(R.id.text_city);
        }
    }
}
