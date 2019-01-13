package com.example.dan.myapplication.Entities;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity
public class Proba {

    @ColumnInfo(name = "nume")
    @SerializedName("nume")
    @Expose
    private String nume;

    @ColumnInfo(name = "swimstyle")
    @SerializedName("swimstyle")
    @Expose
    private String swimstyle;

    @ColumnInfo(name = "distanta")
    @SerializedName("distanta")
    @Expose
    private Double distanta;

    @PrimaryKey
    @SerializedName("pk")
    @Expose
    private Integer pk;


    @ColumnInfo(name = "newdata")
    boolean newData = false;

    @Override
    public String toString() {
        return "Proba{" +
                "nume='" + nume + '\'' +
                ", swimstyle='" + swimstyle + '\'' +
                ", pk=" + pk +
                '}';
    }

    public void setNewData(boolean newData) {
        this.newData = newData;
    }

    public boolean isNewData() {
        return newData;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    @Ignore
    public Proba() {
    }

    @Ignore
    public Proba(String nume, String swimstyle, Double distanta){
        super();
        this.nume = nume;
        this.swimstyle = swimstyle;
        this.distanta = distanta;
    }


    public Proba(String nume, String swimstyle, Double distanta, Integer pk, boolean newData) {
        super();
        this.nume = nume;
        this.swimstyle = swimstyle;
        this.distanta = distanta;
        this.pk = pk;
        this.newData = newData;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getSwimstyle() {
        return swimstyle;
    }

    public void setSwimstyle(String swimstyle) {
        this.swimstyle = swimstyle;
    }

    public Double getDistanta() {
        return distanta;
    }

    public void setDistanta(Double distanta) {
        this.distanta = distanta;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }


}