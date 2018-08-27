package com.example.iycomo.mythoughts;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {ThoughtsModel.class}, version = 1, exportSchema = false)
//@TypeConverters(DateConverter.class)
public abstract class ThoughtsDatabase extends RoomDatabase{

    public abstract ThoughtsDao thoughtsDao();

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "thoughts";
    private static ThoughtsDatabase sInstance;

    public static ThoughtsDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        ThoughtsDatabase.class, ThoughtsDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }
}