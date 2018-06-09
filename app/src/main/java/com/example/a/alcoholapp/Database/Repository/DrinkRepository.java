package com.example.a.alcoholapp.Database.Repository;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.a.alcoholapp.Database.AppDatabase;
import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.Database.Dao.DrinkDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

public class DrinkRepository {

    private DrinkDao mDrinkDao;
    private LiveData<List<Drink>> mAllDrinks;

    @Inject
    public DrinkRepository(AppDatabase db) {
        mDrinkDao = db.drinkDao();
        mAllDrinks = mDrinkDao.getAll();
    }

    public LiveData<List<Drink>> getAllDrinks() {
        return mAllDrinks;
    }

    public void insert (Drink drink) {
        new insertAsyncTask(mDrinkDao).execute(drink);
    }

    public void delete (long id) {
        new deleteAsyncTask(mDrinkDao).execute(id);
    }

    private static class insertAsyncTask extends AsyncTask<Drink, Void, Void> {

        private DrinkDao mAsyncTaskDao;

        insertAsyncTask(DrinkDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Drink... params) {
            System.out.println("In DrinkRepository");
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Long, Void, Void> {

        private DrinkDao mAsyncTaskDao;

        deleteAsyncTask(DrinkDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Long... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}