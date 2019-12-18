package com.example.the_weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterDescriptionWeather adapterDescriptionW;

    List<DetailedWeather> detailedWeathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView= findViewById(R.id.recycler_view_two);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        Intent intent =  getIntent();
        int cityId = intent.getIntExtra("com.example.the_weather.cityId", 0);
        OkHttpClient client = new OkHttpClient();
        final String url = "https://api.openweathermap.org/data/2.5/forecast?id="+cityId+"&units=metric&appid=0d1aafb0e2b156451320ab72d07d2cd6";
        Request request = new Request.Builder().
                url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    try{
                        JSONObject jsonObject = new JSONObject(myResponse);
                        JSONArray jsArray = jsonObject.getJSONArray("list");
                        JSONObject city = jsonObject.getJSONObject("city");
                        String cityName = city.getString("name");


                        for (int i = 0; i <jsArray.length() ; i++) {
                            JSONObject secondJson = jsArray.getJSONObject(i);
                            JSONObject main = secondJson.getJSONObject("main");

                            double temperature = main.getDouble("temp");
                            int humidity = main.getInt("humidity");
                            JSONObject wind = secondJson.getJSONObject("wind");
                            double speed = wind.getDouble("speed");
                            JSONArray weather = secondJson.getJSONArray("weather");
                            JSONObject weatherAll = (JSONObject) weather.get(0);
                            String icon = weatherAll.getString("icon");
                            String description = weatherAll.getString("description");
                            String date = secondJson.getString("dt_txt");


                           DetailedWeather detailedWeather = new DetailedWeather();
                           detailedWeather.setCityName(cityName);
                           detailedWeather.setDate(date);
                           detailedWeather.setDescription(description);
                           detailedWeather.setHumidity(humidity);
                           detailedWeather.setTemperature((int) temperature);
                           detailedWeather.setWind(speed);
                           detailedWeather.setIcon(icon);
                           detailedWeathers.add(detailedWeather);

                        }

                        SecondActivity.this.runOnUiThread(new Runnable(){
                            @Override
                            public void run() {
                                if (detailedWeathers!=null && !detailedWeathers.isEmpty()) {
                                        adapterDescriptionW= new AdapterDescriptionWeather((ArrayList<DetailedWeather>) detailedWeathers );
                                        recyclerView.setAdapter(adapterDescriptionW);

                                }

                            }

                        });

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

}
