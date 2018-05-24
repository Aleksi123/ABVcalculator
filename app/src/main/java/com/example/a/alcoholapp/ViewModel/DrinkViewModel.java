package com.example.a.alcoholapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.Database.Repository.DrinkRepository;

import java.util.List;

public class DrinkViewModel extends AndroidViewModel {
    private DrinkRepository mRepository;

    private LiveData<List<Drink>> mAllDrinks;

    public DrinkViewModel (Application application) {
        super(application);
        mRepository = new DrinkRepository(application);
        mAllDrinks = mRepository.getAllDrinks();
    }

    public LiveData<List<Drink>> getAllDrinks() { return mAllDrinks; }

    public void insert(Drink drink) { mRepository.insert(drink); }
}
