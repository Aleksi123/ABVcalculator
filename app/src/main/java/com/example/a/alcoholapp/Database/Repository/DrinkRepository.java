package com.example.a.alcoholapp.Database.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.a.alcoholapp.Database.AppDatabase;
import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.Database.Dao.DrinkDao;

import java.util.List;

public class DrinkRepository {

    private DrinkDao mDrinkDao;
    private LiveData<List<Drink>> mAllDrinks;

    public DrinkRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDrinkDao = db.drinkDao();
        mAllDrinks = mDrinkDao.getAll();
    }

    public LiveData<List<Drink>> getAllDrinks() {
        return mAllDrinks;
    }

    public void insert (Drink drink) {
        new insertAsyncTask(mDrinkDao).execute(drink);
    }

    private static class insertAsyncTask extends AsyncTask<Drink, Void, Void> {

        private DrinkDao mAsyncTaskDao;

        insertAsyncTask(DrinkDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Drink... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}