package com.pdm00057616.labonotesver2.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.pdm00057616.labonotesver2.database.DB;
import com.pdm00057616.labonotesver2.database.daos.UserDao;
import com.pdm00057616.labonotesver2.models.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    static User user;

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

    public static void setUser(User aux){
        user=aux;
    }

    public LiveData<User> getUser() {
        return userDao.getUser();
    }

    public User getUserByUsername(String username){
        new getUsernameAsyncTask(userDao).execute(username);
        return user;
    }

    public void nukeTable(){
        new nukeTableAsyncTask(userDao).execute();
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

    private static class getUsernameAsyncTask extends AsyncTask<String, Void, User>{

        private UserDao userDao;

        private getUsernameAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.getUserByUsername(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            UserRepository.setUser(user);
        }
    }

    private static class nukeTableAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;

        private nukeTableAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.nuke();
            return null;
        }
    }

}
