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


public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.WeatherViewHolder>{
    private ArrayList<Weather> weatherList;
    private OnItemClickListener clickListener ;

    public AdapterWeather(ArrayList<Weather> weatherList) {
        this.weatherList= weatherList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.cityName.setText(weatherList.get(position).getCityName());
        holder.description.setText(weatherList.get(position).getDescription());
        holder.temperature.setText(weatherList.get(position).getTemperature()+" C");
        String url = "https://openweathermap.org/img/wn/"+weatherList.get(position).getIcon()+"@2x.png";
        Picasso.get().load(url).into(holder.icon);



    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        TextView temperature;
        ImageView icon;
        TextView description;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.tv_city_name);
            temperature = itemView.findViewById(R.id.tv_temperature);
            icon = itemView.findViewById(R.id.iv_icon);
            description = itemView.findViewById(R.id.tv_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener!= null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
