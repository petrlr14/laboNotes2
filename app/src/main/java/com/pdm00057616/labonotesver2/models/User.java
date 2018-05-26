package com.pdm00057616.labonotesver2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "logged")
    private int isLogged;

    public User(@NonNull String username, int isLogged) {
        this.username = username;
        this.isLogged=isLogged;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public int getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(int isLogged) {
        this.isLogged = isLogged;
    }
}
