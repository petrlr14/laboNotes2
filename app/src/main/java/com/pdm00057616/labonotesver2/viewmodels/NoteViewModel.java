package com.pdm00057616.labonotesver2.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pdm00057616.labonotesver2.database.repositories.NoteRepository;
import com.pdm00057616.labonotesver2.models.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
    }

    public LiveData<List<Note>> getNotesByUser(String user) {
        return noteRepository.getAllNotes(user);
    }

    public void insert(Note note){
        noteRepository.insertNote(note);
    }
}
