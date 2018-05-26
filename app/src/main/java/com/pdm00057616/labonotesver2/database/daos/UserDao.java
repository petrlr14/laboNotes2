package com.pdm00057616.labonotesver2.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pdm00057616.labonotesver2.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT*FROM User WHERE username like (:name)")
    User getUser(String name);

    @Query("SELECT*FROM User")
    LiveData<List<User>> getAllUsers();

}
