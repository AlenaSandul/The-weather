package com.example.the_weather;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDescriptionWeather extends RecyclerView.Adapter<AdapterDescriptionWeather.DescriptionWViewHolder> {
    private ArrayList<DetailedWeather> detailedWeathers;

    public AdapterDescriptionWeather(ArrayList<DetailedWeather> detailedWeathers) {
        this.detailedWeathers = detailedWeathers;
    }

    @NonNull
    @Override
    public AdapterDescriptionWeather.DescriptionWViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_description_list;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        AdapterDescriptionWeather.DescriptionWViewHolder viewHolder = new AdapterDescriptionWeather.DescriptionWViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionWViewHolder holder, int position) {
        holder.cityName.setText(detailedWeathers.get(position).getCityName());
        holder.description.setText(detailedWeathers.get(position).getDescription());
        String url = "https://openweathermap.org/img/wn/"+detailedWeathers.get(position).getIcon()+"@2x.png";
        holder.date.setText(detailedWeathers.get(position).getDate());
        Picasso.get().load(url).into(holder.icon);
        int temp = detailedWeathers.get(position).getTemperature();
        int hum  = detailedWeathers.get(position).getHumidity();
        double win = detailedWeathers.get(position).getWind();
        holder.allDescription.setText("Темп: "+temp+"     "+"Влажн: "+hum+"%"+"     "+"Ветер: "+win+"м/с");


    }

    @Override
    public int getItemCount() {
        return detailedWeathers.size();
    }

    class DescriptionWViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView icon;
        TextView description;
        TextView allDescription;
        TextView cityName;


        public DescriptionWViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            icon = itemView.findViewById(R.id.icon_second);
            description = itemView.findViewById(R.id.description_second);
            allDescription = itemView.findViewById(R.id.all_description);
            cityName = itemView.findViewById(R.id.city_name);

        }
    }
}
