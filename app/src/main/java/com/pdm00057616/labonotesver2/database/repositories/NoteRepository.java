package com.pdm00057616.labonotesver2.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.pdm00057616.labonotesver2.database.DB;
import com.pdm00057616.labonotesver2.database.daos.NoteDao;
import com.pdm00057616.labonotesver2.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        DB db=DB.getInstance(application);
        this.noteDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotes(String user) {
        return noteDao.getNotesByUsername(user);
    }

    public void insertNote(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
}
