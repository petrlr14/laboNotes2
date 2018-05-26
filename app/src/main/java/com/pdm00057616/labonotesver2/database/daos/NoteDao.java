package com.pdm00057616.labonotesver2.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pdm00057616.labonotesver2.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Query("SELECT*FROM Note WHERE user_id like (:username)")
    LiveData<List<Note>> getNotesByUsername(String username);

    @Query("SELECT*FROM Note WHERE id=(:id)")
    Note getNotesByID(int id);
}
