package com.example.the_weather;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AdapterWeather.OnItemClickListener {
    private RecyclerView recyclerView;
    private AdapterWeather adapterWeather;

    List<Weather> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        OkHttpClient client = new OkHttpClient();
    final String url = "https://api.openweathermap.org/data/2.5/group?id=709930,706483,702550,707471,689558,698740,703448&units=metric&APPID=0d1aafb0e2b156451320ab72d07d2cd6";
    Request request = new Request.Builder().
            url(url).
            build();
        client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            if (response.isSuccessful()) {

                final String myResponse = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(myResponse);
                    JSONArray jsArray = jsonObject.getJSONArray("list");

                    for (int i = 0; i < jsArray.length(); i++) {
                        JSONObject jsonSecondary = jsArray.getJSONObject(i);
                        JSONObject main = jsonSecondary.getJSONObject("main");
                        int temperature = main.getInt("temp");
                        String cityName = jsonSecondary.getString("name");
                        JSONArray weather = jsonSecondary.getJSONArray("weather");
                        JSONObject weatherAll = (JSONObject) weather.get(0);
                        String icon = weatherAll.getString("icon");
                        String description = weatherAll.getString("description");
                        long idCity = jsonSecondary.getLong("id");

                        Weather dataWeather = new Weather();
                        dataWeather.setIdCity((int) idCity);
                        dataWeather.setCityName(cityName);
                        dataWeather.setDescription(description);
                        dataWeather.setIcon(icon);
                        dataWeather.setTemperature(temperature);
                        weatherList.add(dataWeather);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (weatherList!=null && !weatherList.isEmpty()) {
                        adapterWeather = new AdapterWeather((ArrayList<Weather>) weatherList);
                        recyclerView.setAdapter(adapterWeather);
                        adapterWeather.setOnItemClickListener(MainActivity.this);
                    }

                }

            });
        }

    });
}

    @Override
    public void onItemClick(int position) {
        Weather resultItem = weatherList.get(position);
        int cityId = resultItem.getIdCity();

        Intent detailIntent = new Intent(this, SecondActivity.class);
        detailIntent.putExtra("com.example.the_weather.cityId",cityId);
        startActivity(detailIntent);


    }
}


