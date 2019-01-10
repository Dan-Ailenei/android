package com.example.dan.myapplication.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.myapplication.Entities.OpenChannel;
import com.example.dan.myapplication.R;

import java.util.List;

// template-u contine clasa care tine datele
public class ChannelsListAdapter extends RecyclerView.Adapter<ChannelsListAdapter.ProbeListHolder> {
    private Context mContext;
    private List<OpenChannel> probaList;

    public ChannelsListAdapter(Context context, List<OpenChannel> probe){
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
        OpenChannel proba = probaList.get(i);
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
