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

    public UserInfoViewModel (Application application) {
        super(application);
        mRepository = new UserInfoRepository(application);
        mAllUserInfos = mRepository.getAllUserInfos();
    }

    public LiveData<List<UserInfo>> getAllDrinks() { return mAllUserInfos; }

    public void insert(UserInfo userInfo) { mRepository.insert(userInfo); }

    public void delete(int id){ mRepository.delete(id);}
}