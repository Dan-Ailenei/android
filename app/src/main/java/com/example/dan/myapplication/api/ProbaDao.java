package com.example.dan.myapplication.api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.dan.myapplication.Entities.Proba;

import java.util.List;


@Dao
public interface ProbaDao {

    @Query("Select * from Proba")
    List<Proba> getProbe();

    @Insert
    void insertAll(List<Proba> probe);

    @Insert
    void insertProba(Proba proba);

    @Query("DELETE FROM Proba")
    public void nukeTable();

}
