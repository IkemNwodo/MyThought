package com.example.iycomo.mythoughts;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "thought")
public class ThoughtsModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String description;

    @ColumnInfo(name = "date")
    private long mDate;

    @Ignore
    public ThoughtsModel(@NonNull String title, @NonNull String description, @NonNull long date){
        this.title = title;
        this.description = description;
        this.mDate = date;
    }


    public ThoughtsModel(int id, String title, String description, long date){
        this.id = id;
        this.title = title;
        this.description = description;
        this.mDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return mDate;
    }

}
