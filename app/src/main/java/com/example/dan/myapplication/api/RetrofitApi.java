package com.example.dan.myapplication.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static OkHttpClient OkHttpClient = null;
    private static ProbaResource ourInstance = null;

    public static ProbaResource getInstance() {
        if(ourInstance == null){

            OkHttpClient = new OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.SECONDS)
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ProbaResource.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(OkHttpClient)
                    .build();

            ourInstance =retrofit.create(ProbaResource.class);
        }

        return ourInstance;
    }

    private RetrofitApi() {
    }
}
