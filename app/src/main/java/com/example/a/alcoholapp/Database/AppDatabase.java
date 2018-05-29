package com.example.a.alcoholapp.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.a.alcoholapp.Database.Dao.DrinkDao;
import com.example.a.alcoholapp.Database.Dao.UserInfoDao;
import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.Database.Entity.UserInfo;

@Database(entities = {Drink.class, UserInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DrinkDao drinkDao();
    public abstract UserInfoDao userInfoDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "drink_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DrinkDao mDao;
        private final UserInfoDao mDao2;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.drinkDao();
            mDao2 = db.userInfoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            Drink drink = new Drink("Beer","33","141");
            mDao.insert(drink);

            return null;
        }
    }
}