package com.pdm00057616.labonotesver2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = {@Index("user_id")},foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "username",
        childColumns = "user_id",
        onDelete = CASCADE))
public class Category {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "user_id")
    private String userID;

    public Category(String name, String userID) {
        this.name = name;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public void setId(int id) {
        this.id = id;
    }
}
