package com.pdm00057616.labonotesver2.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdm00057616.labonotesver2.database.DB;
import com.pdm00057616.labonotesver2.database.daos.UserDao;
import com.pdm00057616.labonotesver2.models.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        DB db=DB.getInstance(application);
        this.userDao=db.userDao();
        this.allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
    }


    public LiveData<User> getUser() {
        return userDao.getUser();
    }

    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mUserDao;

        private insertAsyncTask(UserDao mUserDao) {
            this.mUserDao = mUserDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

}
