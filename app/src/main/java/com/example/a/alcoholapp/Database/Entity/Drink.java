package com.example.a.alcoholapp.Database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "drink_table")
public class Drink {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "drink")
    private String mDrink;

    @ColumnInfo(name = "cl")
    private  String mCl;

    @ColumnInfo(name = "calories")
    private String mCalories;

    public Drink(@NonNull String drink, String cl, String calories) {
        this.mDrink = drink;
        this.mCl = cl;
        this.mCalories = calories;
    }

    public String getDrink(){return this.mDrink;}

    public String getCl(){return  this.mCl;}

    public String getCalories(){return this.mCalories;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}