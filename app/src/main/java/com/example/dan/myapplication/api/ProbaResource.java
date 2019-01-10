package com.example.dan.myapplication.api;

import com.example.dan.myapplication.Entities.Channels;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ProbaResource {
    String BASE_URL = "https://api.sendbird.com/v3/";
    String apiToken = "API-Token: 7635b3857640f7f7e6bfefc8d68b8db5e04cbc07";

    @Headers({apiToken})
    @GET("open_channels")
    Call<Channels> getPagini(@Query("limit") Integer limit);

}
