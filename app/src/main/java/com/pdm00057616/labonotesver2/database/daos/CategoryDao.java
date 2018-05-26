package com.pdm00057616.labonotesver2.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.pdm00057616.labonotesver2.models.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("SELECT*FROM Category")
    LiveData<List<Category>> getAllCategory();



}
