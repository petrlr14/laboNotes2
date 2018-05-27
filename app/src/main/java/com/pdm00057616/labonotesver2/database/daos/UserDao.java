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

    @Query("SELECT*FROM User WHERE logged=1")
    LiveData<User> getUser();

    @Query("SELECT*FROM User")
    LiveData<List<User>> getAllUsers();

    @Query("UPDATE User SET logged=0 WHERE username = :username")
    void setLogout(String username);

    @Query("UPDATE User SET logged=1 WHERE username like (:username)")
    void setLogin(String username);

    @Query("SELECT*FROM User WHERE username LIKE :username")
    User getUserByUsername(String username);
}
