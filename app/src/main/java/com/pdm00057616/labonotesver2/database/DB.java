package com.pdm00057616.labonotesver2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.pdm00057616.labonotesver2.database.daos.CategoryDao;
import com.pdm00057616.labonotesver2.database.daos.NoteDao;
import com.pdm00057616.labonotesver2.database.daos.UserDao;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.models.Note;
import com.pdm00057616.labonotesver2.models.User;

@Database(entities = {User.class, Note.class, Category.class},
        exportSchema = false, version = 1)
public abstract class DB extends RoomDatabase{

    private static volatile DB db;
    private static final String DB_NAME="notesDatabase.db";

    public static synchronized DB getInstance(Context context) {
        if(db==null){
            db=create(context);
        }
        return db;
    }

    private static DB create(Context context){
        return Room.databaseBuilder(context,
                DB.class, DB_NAME).build();
    }

    public abstract UserDao userDao();
    public abstract NoteDao noteDao();
    public abstract CategoryDao categoryDao();
}