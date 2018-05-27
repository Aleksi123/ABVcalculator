package com.example.a.alcoholapp.di;

import com.example.a.alcoholapp.ViewModel.DrinkViewModel;

import dagger.Provides;
import dagger.Subcomponent;

/**
 * Defines a Subcomponent interface which will provide all the view models used in this project
 */
@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    //View Model providers are defined here
    //WHEN MORE VIEW MODELS ARE CREATED DEFINE THEIR PROVIDERS HERE!
    DrinkViewModel provideDrinkViewModel();
}
