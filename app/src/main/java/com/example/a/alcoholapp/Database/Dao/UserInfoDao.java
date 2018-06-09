package com.example.a.alcoholapp.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.a.alcoholapp.Database.Entity.UserInfo;
import java.util.List;

@Dao
public interface UserInfoDao {
    @Query("SELECT * FROM user_info_table")
    LiveData<List<UserInfo>> getAll();

    @Query("SELECT weight FROM user_info_table")
    LiveData<String> getUserWeight();

    @Query("SELECT gender FROM user_info_table")
    LiveData<String> getUserGender();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserInfo userInfo);

    @Delete
    void delete(UserInfo userInfo);

    @Query("DELETE FROM user_info_table")
    void deleteAll();
}