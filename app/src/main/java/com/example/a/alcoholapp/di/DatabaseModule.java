package com.example.a.alcoholapp.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.a.alcoholapp.Database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "drink_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
