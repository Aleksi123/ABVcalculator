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
    private LiveData<String> mUserWeight;
    private LiveData<String> mUserGender;

    public UserInfoRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mUserInfoDao = db.userInfoDao();
        mAllUserInfos = mUserInfoDao.getAll();
        mUserWeight = mUserInfoDao.getUserWeight();
        mUserGender = mUserInfoDao.getUserGender();
    }

    public LiveData<List<UserInfo>> getAllUserInfos() {
        return mAllUserInfos;
    }

    public LiveData<String> getmUserWeight() {
        return mUserWeight;
    }

    public LiveData<String> getmUserGender() {
        return mUserGender;
    }

    public void insert (UserInfo userInfo) {
        new insertAsyncTask(mUserInfoDao).execute(userInfo);
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
}