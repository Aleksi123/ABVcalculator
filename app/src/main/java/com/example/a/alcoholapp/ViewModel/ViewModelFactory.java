package com.example.a.alcoholapp.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;

import com.example.a.alcoholapp.di.ViewModelSubComponent;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Provides view model instances
 * https://github.com/googlesamples/android-architecture-components/tree/e33782ba54ebe87f7e21e03542230695bc893818/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 * https://proandroiddev.com/mvvm-architecture-viewmodel-and-livedata-part-2-di-1a6b1f96d84b
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {
    /**
     * This map takes a .class variable as key. The original class has to extend ViewModel.
     */
    private final ArrayMap<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(final ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();
        creators.put(DrinkViewModel.class, new Provider<ViewModel>() {
            @Override
            public ViewModel get() {
                return viewModelSubComponent.provideDrinkViewModel();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
