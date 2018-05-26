package com.example.a.alcoholapp.Database.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.a.alcoholapp.Database.AppDatabase;
import com.example.a.alcoholapp.Database.Dao.UserInfoDao;
import com.example.a.alcoholapp.Database.Entity.UserInfo;

import java.util.List;

public class UserInfoRepository {

    private UserInfoDao mUserInfoDao;
    private LiveData<List<UserInfo>> mAllUserInfos;

    public UserInfoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserInfoDao = db.userInfoDao();
        mAllUserInfos = mUserInfoDao.getAll();
    }

    public LiveData<List<UserInfo>> getAllUserInfos() {
        return mAllUserInfos;
    }

    public void insert (UserInfo userInfo) {
        new insertAsyncTask(mUserInfoDao).execute(userInfo);
    }

    public void delete (int id) {
        new deleteAsyncTask(mUserInfoDao).execute(id);
    }

    private static class insertAsyncTask extends AsyncTask<UserInfo, Void, Void> {

        private UserInfoDao mAsyncTaskDao;

        insertAsyncTask(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final UserInfo... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private UserInfoDao mAsyncTaskDao;

        deleteAsyncTask(UserInfoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}