package com.example.the_weather;


public class DetailedWeather {
    private  String date;
    private String icon;
    private String description;
    private int temperature;
    private String cityName;
    private double wind;
    private  int humidity;

    public String getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public double getWind() {
        return wind;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
