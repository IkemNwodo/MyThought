package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class AddThoughtViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application application;
    private final int thoughtId;
    public AddThoughtViewModelFactory(Application application, int thoughtId) {
        this.application = application;
        this.thoughtId = thoughtId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // noinspection unchecked
        return (T) new AddThoughtViewModel(application, thoughtId);
    }
}
