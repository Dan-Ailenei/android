package com.example.dan.myapplication.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.dan.myapplication.AddProbaActivity;
import com.example.dan.myapplication.Entities.Proba;
import com.example.dan.myapplication.MainActivity;
import com.example.dan.myapplication.R;
import com.example.dan.myapplication.api.AppDatabase;
import com.example.dan.myapplication.viewmodel.ProbeViewModel;

import java.util.List;

public class ProbeListFragment extends Fragment {
    private ProbeViewModel probaViewModel;
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
        probaList = view.findViewById(R.id.proba_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        probaList.setLayoutManager(new LinearLayoutManager(getActivity()));
        probaViewModel = ViewModelProviders.of(this).get(ProbeViewModel.class);

        Button add_button = (Button) getView().findViewById(R.id.button2);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddProbaActivity.class);
                getContext().startActivity(intent);
            }
        });

        probaViewModel.getProbe(MainActivity.getToken(), AppDatabase.getInstance(getContext()).daoAccess()).observe(this, new Observer<List<Proba>>() {
            @Override
            public void onChanged(List<Proba> probe) {
                ProbeListAdapter probeAdapter = new ProbeListAdapter(getActivity(), probe);
                probaList.setAdapter(probeAdapter);
            }
        });


    }
}
