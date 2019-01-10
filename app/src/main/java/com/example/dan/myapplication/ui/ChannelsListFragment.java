package com.example.dan.myapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.myapplication.Entities.OpenChannel;
import com.example.dan.myapplication.R;
import com.example.dan.myapplication.viewmodel.ChannelsViewModel;

import java.util.List;

public class ChannelsListFragment extends Fragment {
    private ChannelsViewModel probaViewModel;
    private RecyclerView probaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.cahnnels_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        probaList = view.findViewById(R.id.proba_list); // recyclerview
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        probaList.setLayoutManager(new LinearLayoutManager(getActivity()));

        probaViewModel = ViewModelProviders.of(this).get(ChannelsViewModel.class);

        probaViewModel.getProbe().observe(this, new Observer<List<OpenChannel>>() {
            @Override
            public void onChanged(List<OpenChannel> probe) {
                ChannelsListAdapter probeAdapter = new ChannelsListAdapter(getActivity(), probe);
                probaList.setAdapter(probeAdapter);
            }
        });

    }
}
