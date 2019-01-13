package com.example.dan.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.util.Log;

import com.example.dan.myapplication.Entities.Proba;
import com.example.dan.myapplication.Entities.Probe;
import com.example.dan.myapplication.api.ProbaDao;
import com.example.dan.myapplication.api.ProbaResource;
import com.example.dan.myapplication.api.RetrofitApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProbeViewModel extends ViewModel {
    public static final String TAG = ProbeViewModel.class.getCanonicalName();
    private MutableLiveData<List<Proba>> probe;
    private Handler mHandler = new Handler();

    public LiveData<List<Proba>> getProbe(final String token, final ProbaDao access){
        probe = new MutableLiveData<List<Proba>>();
        loadProbe(token, access);
        final Runnable load = new Runnable() {
            @Override
            public void run() {
                loadProbe(token, access);
                mHandler.postDelayed(this, 10000);
            }
        };
        mHandler.postDelayed(load, 10000);

        return probe;
    }


    private void getDataLocal(final ProbaDao access){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Proba> old_probe = access.getProbe();
                probe.postValue(old_probe);
            }
        }).start();
    }


    private void loadProbe(final String token, final ProbaDao access){
        Log.d(TAG, "loadPages");

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Proba> old_probe = access.getProbe();
                List<Proba> need_update = new ArrayList<>();
                for(Proba old: old_probe) {
                    if (old.isNewData()) {
                        need_update.add(old);
                    }
                }

                //notify server about changed entities
                ProbaResource api = RetrofitApi.getInstance();
                Call<Void> creare_call = api.creare_probe(need_update);
                creare_call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        requestAndUpdate(access);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        getDataLocal(access);
                    }
                });
            }


            public void requestAndUpdate(final ProbaDao access){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ProbaResource.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ProbaResource api = retrofit.create(ProbaResource.class);
                Map<String, String> map = new HashMap<>();
                map.put(api.session_token_header, "sessionid="+token);
                final Call<Probe> call = api.getPagini(map);

                call.enqueue(new Callback<Probe>() {
                    @Override
                    public void onResponse(Call<Probe> call, Response<Probe> response) {
                        Log.d(TAG, "loadPages succeded");

                        final List<Proba> lista = response.body().getProbe();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                access.nukeTable();
                                access.insertAll(lista);
                                probe.postValue(lista);
                            }
                        }).start();
                    }

                    @Override
                    public void onFailure(Call<Probe> call, Throwable t) {
                        Log.d(TAG, "loadPagesfailed", t);
                        getDataLocal(access);
                    }


                });
            }
        }) .start();


    }


}
