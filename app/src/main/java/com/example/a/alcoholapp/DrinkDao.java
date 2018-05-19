package com.example.a.alcoholapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DrinkDao {
    @Query("SELECT * FROM drink")
    List<Drink> getAll();

    @Query("SELECT * FROM drink WHERE id IN (:drinkIds)")
    List<Drink> loadAllByIds(int[] drinkIds);

    @Insert
    void insertAll(Drink... drinks);

    @Delete
    void delete(Drink user);
}