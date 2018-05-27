package com.example.a.alcoholapp.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.a.alcoholapp.Activity.MainActivity;
import com.example.a.alcoholapp.AlcoholApp;
import com.example.a.alcoholapp.Database.AppDatabase;
import com.example.a.alcoholapp.Database.Repository.DrinkRepository;
import com.example.a.alcoholapp.ViewModel.DrinkViewModel;
import com.example.a.alcoholapp.ViewModel.ViewModelFactory;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    //Below are methods annotated with @Provides
    //These methods are methods that instantiate a dependency

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "drink_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ViewModelFactory(viewModelSubComponent.build());
    }

    @Provides
    DrinkRepository provideDrinkRepository(AppDatabase db){
        return new DrinkRepository(db);
    }

    //TODO look for a better way to bring ViewModelSubComponent
    @Provides
    ViewModelSubComponent provideViewModelSubComponent(final Application app, final DrinkRepository repo){
        return new ViewModelSubComponent() {
            @Override
            public DrinkViewModel provideDrinkViewModel() {
                return new DrinkViewModel(app, repo);
            }
        };
    }
}

