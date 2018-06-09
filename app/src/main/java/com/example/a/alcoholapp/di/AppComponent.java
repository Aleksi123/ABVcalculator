package com.example.a.alcoholapp.di;

import android.app.Application;

import com.example.a.alcoholapp.Activity.MainActivity;
import com.example.a.alcoholapp.AlcoholApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        DatabaseModule.class,
        BuildersModule.class})
public interface AppComponent extends AndroidInjector<AlcoholApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(Application app);
}
