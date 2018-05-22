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

    public Drink(int id, String drinkName, String drinkCalories) {
        this.id = id;
        this.drinkName = drinkName;
        this.drinkCalories = drinkCalories;
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

}