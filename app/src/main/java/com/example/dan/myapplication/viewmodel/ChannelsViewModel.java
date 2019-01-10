package com.example.dan.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.dan.myapplication.Entities.Channels;
import com.example.dan.myapplication.Entities.OpenChannel;
import com.example.dan.myapplication.api.ProbaResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChannelsViewModel extends ViewModel {
    public static final String TAG = ChannelsViewModel.class.getCanonicalName();
    private MutableLiveData<List<OpenChannel>> probe;

    public LiveData<List<OpenChannel>> getProbe(){
        if (probe == null){
            probe = new MutableLiveData<List<OpenChannel>>();
            loadProbe();
        }
        return probe;
    }

    public void loadProbe(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProbaResource.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProbaResource api = retrofit.create(ProbaResource.class);
        Call<Channels> call = api.getPagini(20);
        Log.d(TAG, "loadPages");
        call.enqueue(new Callback<Channels>() {
            @Override
            public void onResponse(Call<Channels> call, Response<Channels> response) {
                Log.d(TAG, "loadPages succeded");
                probe.setValue(response.body().getChannels());
            }

            @Override
            public void onFailure(Call<Channels> call, Throwable t) {
                Log.d(TAG, "loadPagesfailed", t);
            }
        });

    }
}
