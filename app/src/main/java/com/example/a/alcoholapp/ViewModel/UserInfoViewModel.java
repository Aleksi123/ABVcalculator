package com.example.a.alcoholapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.a.alcoholapp.Database.Entity.UserInfo;
import com.example.a.alcoholapp.Database.Repository.UserInfoRepository;

import java.util.List;

public class UserInfoViewModel extends AndroidViewModel {
    private UserInfoRepository mRepository;

    private LiveData<List<UserInfo>> mAllUserInfos;
    private LiveData<String> mUserWeight;
    private LiveData<String> mUserGender;

    public UserInfoViewModel (Application application) {
        super(application);
        mRepository = new UserInfoRepository(application);
        mAllUserInfos = mRepository.getAllUserInfos();
        mUserWeight = mRepository.getmUserWeight();
        mUserGender = mRepository.getmUserGender();
    }

    public LiveData<List<UserInfo>> getAllUserInfos() { return mAllUserInfos; }

    public LiveData<String> getmUserWeight() {
        return mUserWeight;
    }

    public LiveData<String> getmUserGender() {
        return mUserGender;
    }

    public void insert(UserInfo userInfo) { mRepository.insert(userInfo); }

}