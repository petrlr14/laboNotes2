package com.pdm00057616.labonotesver2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pdm00057616.labonotesver2.database.repositories.UserRepository;
import com.pdm00057616.labonotesver2.models.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository(application);
    }

    public User getUserByUsername(String name){
        return userRepository.getUserByUsername(name);
    }

    public LiveData<List<User>> getAllUser(){
        return userRepository.getAllUsers();
    }

    public void inserUsername(User user){
        userRepository.insert(user);
    }
}
