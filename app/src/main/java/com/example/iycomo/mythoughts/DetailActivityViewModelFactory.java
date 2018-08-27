package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class DetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private int thoughtId;

    public DetailActivityViewModelFactory(Application application, int thoughtId){
        this.application = application;
        this.thoughtId = thoughtId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new DetailActivityViewModel(application, thoughtId);
    }
}
