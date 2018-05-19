package com.example.a.alcoholapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Drink.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DrinkDao drinkDao();
    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "drink_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}