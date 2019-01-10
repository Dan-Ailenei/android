package com.example.dan.myapplication.Entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Channels {

    @SerializedName("channels")
    @Expose
    private List<OpenChannel> channels = null;

    /**
     * No args constructor for use in serialization
     */
    public Channels() {
    }

    /**
     * @param channels
     */
    public Channels(List<OpenChannel> channels) {
        super();
        this.channels = channels;
    }

    public List<OpenChannel> getChannels() {
        return channels;
    }

    public void setChannels(List<OpenChannel> channels) {
        this.channels = channels;
    }


}