package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class AddThoughtViewModel extends ViewModel {

    // Thought member variable
    private LiveData<ThoughtsModel> thought;

    // ThoughtRepository member variable
    private ThoughtsRespository thoughtsRespository;

    public AddThoughtViewModel(Application application, int thoughtId){
        thoughtsRespository = new ThoughtsRespository(application);
        thought = thoughtsRespository.getLoadThoughtById(thoughtId);
    }

    // Getter for the thought variable
    public LiveData<ThoughtsModel> getThought() {
        return thought;
    }

    public void insertThought(ThoughtsModel thoughtsModel){
        thoughtsRespository.insert(thoughtsModel);
    }

    public void updateThought(ThoughtsModel thoughtsModel){
        thoughtsRespository.update(thoughtsModel);
    }

}
