package com.tsystems.photocurry.home.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.tsystems.photocurry.home.dao.SearchEntityDao;
import com.tsystems.photocurry.home.model.SearchEntity;
import com.tsystems.photocurry.home.persistence.SearchRoomDatabase;
import com.tsystems.photocurry.home.presenter.DatabaseListener;
import com.tsystems.photocurry.home.presenter.HomePresenter;

/**
 * Created by PerryGarg on 13-05-2018.
 */

public class SearchEntityRepository {

    private SearchEntityDao searchEntityDao;
    private DatabaseListener listener;

    public SearchEntityRepository(Application application, DatabaseListener listener) {
        SearchRoomDatabase db = SearchRoomDatabase.getDatabase(application);
        searchEntityDao = db.searchEntityDao();
        this.listener = listener;
    }

    /**
     * You must call this on a non-UI thread or your app will crash. Like this, Room ensures that you're not doing any long running operations on the main thread, blocking the UI.
     * @param searchEntity entity to be inserted
     */
    public void insert(SearchEntity searchEntity) {
        new InsertAsyncTask(searchEntityDao).execute(searchEntity);
    }

    /**
     * You must call this on a non-UI thread or your app will crash. Like this, Room ensures that you're not doing any long running operations on the main thread, blocking the UI.
     * @param queryText query against which entity has to be fetched
     */
    public void getSearchEntity(String queryText) {
        new GetDataAsyncTask(searchEntityDao).execute(queryText);
    }

    private static class InsertAsyncTask extends AsyncTask<SearchEntity, Void, Void> {

        private SearchEntityDao mAsyncTaskDao;

        InsertAsyncTask(SearchEntityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SearchEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private class GetDataAsyncTask extends AsyncTask<String, Void, SearchEntity> {

        private SearchEntityDao mAsyncTaskDao;

        GetDataAsyncTask(SearchEntityDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected SearchEntity doInBackground(final String... params) {
            return mAsyncTaskDao.getSearchEntityWithQuery(params[0]);
        }

        @Override
        protected void onPostExecute(SearchEntity searchEntity) {
            super.onPostExecute(searchEntity);
            if(listener != null)
            listener.onDBServiceSuccess(searchEntity);
        }
    }

}
