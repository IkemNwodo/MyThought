package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ThoughtsViewModel extends AndroidViewModel {

    private ThoughtsRespository thoughtsRespository;

    private LiveData<List<ThoughtsModel>> loadAllThoughts;

    public ThoughtsViewModel(@NonNull Application application) {
        super(application);
        thoughtsRespository = new ThoughtsRespository(application);
        loadAllThoughts = thoughtsRespository.getLoadAllThoughts();
    }

    public LiveData<List<ThoughtsModel>> getLoadAllThoughts() {
        return loadAllThoughts;
    }

    public void deleteThought(ThoughtsModel thoughtsModel){
        thoughtsRespository.delete(thoughtsModel);
    }
}
