package com.example.iycomo.mythoughts;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ThoughtsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertThought(ThoughtsModel thoughtsModel);

    @Update(onConflict = REPLACE)
    void updateThought(ThoughtsModel thoughtsModel);

    @Delete
    void deleteThought(ThoughtsModel thoughtsModel);

    @Query("SELECT * FROM thought ORDER BY date DESC ")
    LiveData<List<ThoughtsModel>> loadAllThoughts();

    @Query("SELECT * FROM thought WHERE id = :id")
    LiveData<ThoughtsModel> loadThoughtById(int id);
}
