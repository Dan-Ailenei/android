package com.example.dan.myapplication.Entities;

import com.google.gson.annotations.Expose;

public class Token {

    @Expose
    String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
