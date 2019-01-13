package com.example.dan.myapplication.api;

import com.example.dan.myapplication.Entities.Proba;
import com.example.dan.myapplication.Entities.Probe;
import com.example.dan.myapplication.Entities.Token;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ProbaResource {
    String BASE_URL = "http://192.168.0.105:8000";
    String session_token_header = "cookie";

    @GET("probe")
    Call<Probe> getPagini(@HeaderMap Map<String, String> headers);

    @POST("login")
    @FormUrlEncoded
    Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    @GET(".")
    Call<Void> checkOpened();

    @POST("proba/creare_probe")
    Call<Void> creare_probe(@Body List<Proba> probe);
}
