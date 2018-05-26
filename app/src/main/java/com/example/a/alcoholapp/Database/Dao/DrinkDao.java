package com.example.a.alcoholapp.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.a.alcoholapp.Database.Entity.Drink;

import java.util.List;

@Dao
public interface DrinkDao {
    @Query("SELECT * FROM drink_table")
    LiveData<List<Drink>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Drink drink);

    @Insert
    void insertAll(Drink... drinks);

    @Delete
    void delete(Drink user);

    @Query("DELETE FROM drink_table WHERE id = :id")
    int delete(long id);

    @Query("DELETE FROM drink_table")
    void deleteAll();
}