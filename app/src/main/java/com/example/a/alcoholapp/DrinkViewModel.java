package com.example.a.alcoholapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class DrinkViewModel extends AndroidViewModel {
    private DrinkRepository mRepository;

    private LiveData<List<Drink>> mAllDrinks;

    public DrinkViewModel (Application application) {
        super(application);
        mRepository = new DrinkRepository(application);
        mAllDrinks = mRepository.getAllDrinks();
    }

    LiveData<List<Drink>> getAllDrinks() { return mAllDrinks; }

    public void insert(Drink word) { mRepository.insert(word); }
}
