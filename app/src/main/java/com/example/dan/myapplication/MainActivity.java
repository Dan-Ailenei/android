package com.example.dan.myapplication;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.dan.myapplication.api.AppDatabase;
import com.example.dan.myapplication.ui.ProbeListFragment;

public class MainActivity extends AppCompatActivity {

    static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainActivity.token = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ProbeListFragment())
                    .commitNow();
        }
    }
}
