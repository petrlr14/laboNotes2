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
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "user_id")
    private String username;

    public Note(String text, String title, String username) {
        this.text = text;
        this.title = title;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }
}
