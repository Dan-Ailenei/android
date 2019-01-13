package com.example.dan.myapplication.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dan.myapplication.Entities.Proba;
import com.example.dan.myapplication.R;

import java.util.List;

// template-u contine clasa care tine datele
public class ProbeListAdapter extends RecyclerView.Adapter<ProbeListAdapter.ProbeListHolder> {
    private Context mContext;
    private List<Proba> probaList;

    public ProbeListAdapter(Context context, List<Proba> probe){
        this.mContext = context;
        this.probaList = probe;
    }

    @NonNull
    @Override
    public ProbeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.channels_layout, parent, false);
        return new ProbeListHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProbeListHolder probeListHolder, int i) {
        Proba proba = probaList.get(i);
        probeListHolder.textView.setText(proba + "");
    }

    @Override
    public int getItemCount() {
        return probaList.size();
    }

    // clasa asta tine datele
    public class ProbeListHolder extends RecyclerView.ViewHolder{
        TextView textView;

        ProbeListHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
