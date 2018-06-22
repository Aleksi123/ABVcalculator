package com.example.a.alcoholapp.Database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "drink_table")
public class Drink {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "cl")
    private int Cl;

    @ColumnInfo(name = "calories")
    private int Calories;

    @ColumnInfo(name = "alcoholPercentage")
    private double AlcoholPercentage;

    public Drink(){
        name = "";
    }

    public Drink(@NonNull String drink, int cl, int calories, double alcoholPercentage) {
        name = drink;
        Cl = cl;
        Calories = calories;
        AlcoholPercentage = alcoholPercentage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getCl() {
        return Cl;
    }

    public void setCl(int cl) {
        Cl = cl;
    }

    public int getCalories() {
        return Calories;
    }

    public void setCalories(int calories) {
        Calories = calories;
    }

    public double getAlcoholPercentage() {
        return AlcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        AlcoholPercentage = alcoholPercentage;
    }
}