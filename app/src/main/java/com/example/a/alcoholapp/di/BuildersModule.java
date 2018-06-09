package com.example.a.alcoholapp.di;

import com.example.a.alcoholapp.Activity.MainActivity;
import com.example.a.alcoholapp.Activity.ShowDrinksActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module(includes = AndroidInjectionModule.class)
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract ShowDrinksActivity bindMainActivity();

    // Add bindings for other sub-components here
}