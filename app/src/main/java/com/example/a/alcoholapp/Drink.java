package com.example.a.alcoholapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Drink {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "drink_name")
    private String drinkName;

    @ColumnInfo(name = "drink_calories")
    private String drinkCalories;

    @ColumnInfo(name = "drink_per_mil")
    private String drinkPerMil;

    public Drink(int id, String drinkName, String drinkCalories, String drinkPerMil) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkCalories = drinkCalories;
        this.drinkPerMil = drinkPerMil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkCalories() {
        return drinkCalories;
    }

    public void setDrinkCalories(String drinkCalories) {
        this.drinkCalories = drinkCalories;
    }

    public String getDrinkPerMil() {
        return drinkPerMil;
    }

    public void setDrinkPerMil(String drinkPerMil) {
        this.drinkPerMil = drinkPerMil;
    }
}