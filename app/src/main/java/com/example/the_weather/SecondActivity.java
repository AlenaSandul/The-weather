package com.example.the_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent =  getIntent();
        int cityId = intent.getIntExtra("com.example.the_weather.cityId", 0);
        OkHttpClient client = new OkHttpClient();
        final String url = "https://samples.openweathermap.org/data/2.5/forecast?id=709930,706483,702550,707471,689558,698740,703448&appid=b6907d289e10d714a6e88b30761fae22";
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

                        for (int i = 0; i <jsArray.length() ; i++) {

                        }

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
