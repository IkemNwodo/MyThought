package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class DetailActivityViewModel extends ViewModel {

    private ThoughtsRespository thoughtsRespository;

    private LiveData<ThoughtsModel> loadThought;

    public DetailActivityViewModel(Application application, int id){
        thoughtsRespository = new ThoughtsRespository(application);
        loadThought = thoughtsRespository.getLoadThoughtById(id);
    }

    public LiveData<ThoughtsModel> getLoadThought() {
        return loadThought;
    }
}
