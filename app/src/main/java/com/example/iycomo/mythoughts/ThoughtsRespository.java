package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ThoughtsRespository {

    private ThoughtsDao thoughtsDao;
    private LiveData<List<ThoughtsModel>> loadAllThoughts;
    private LiveData<ThoughtsModel> loadThoughtById;

    ThoughtsRespository(Application application){
        ThoughtsDatabase database = ThoughtsDatabase.getInstance(application);
        thoughtsDao = database.thoughtsDao();
        loadAllThoughts = thoughtsDao.loadAllThoughts();
    }


    public void insert(ThoughtsModel thoughtsModel){
        new insertAsyncTask(thoughtsDao).execute(thoughtsModel);
    }

    public void update(ThoughtsModel thoughtsModel){
        new updateAsyncTask(thoughtsDao).execute(thoughtsModel);
    }

    public void delete(ThoughtsModel thoughtsModel){
        new deleteAsyncTask(thoughtsDao).execute(thoughtsModel);
    }

    public LiveData<ThoughtsModel> getLoadThoughtById(int thoughtId) {
        return thoughtsDao.loadThoughtById(thoughtId);
    }

    public LiveData<List<ThoughtsModel>> getLoadAllThoughts() {
        return loadAllThoughts;
    }

    private static class insertAsyncTask extends AsyncTask<ThoughtsModel, Void, Void> {

        private ThoughtsDao mAsyncTaskDao;

        insertAsyncTask(ThoughtsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThoughtsModel... thoughtsModels) {
            mAsyncTaskDao.insertThought(thoughtsModels[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<ThoughtsModel, Void, Void> {

        private ThoughtsDao mAsyncTaskDao;

        updateAsyncTask(ThoughtsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThoughtsModel... thoughtsModels) {
            mAsyncTaskDao.updateThought(thoughtsModels[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ThoughtsModel, Void, Void> {

        private ThoughtsDao mAsyncTaskDao;

        deleteAsyncTask(ThoughtsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ThoughtsModel... thoughtsModels) {
            mAsyncTaskDao.deleteThought(thoughtsModels[0]);
            return null;
        }
    }
}
