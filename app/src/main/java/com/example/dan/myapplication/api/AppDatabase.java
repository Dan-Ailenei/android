package com.example.dan.myapplication.api;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.example.dan.myapplication.Entities.Proba;


@Database(entities = {Proba.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static String dbName = "pdmdb";
    private static AppDatabase INSTANCE = null;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Proba "
                              + " ADD COLUMN newdata INTEGER NOT NULL default 0");

        }
    };

    public static synchronized AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "pdmdb")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }

    public abstract ProbaDao daoAccess() ;
}
