package com.pdm00057616.labonotesver2.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.adapters.NotesRecyclerView;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.models.Note;
import com.pdm00057616.labonotesver2.viewmodels.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotesByCategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoteViewModel noteViewModel;
    private NotesRecyclerView adapter;
    private Context context;
    private Activity activity;
    private List<Note> notes;
    private int resctriction=-1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new NotesRecyclerView();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNotesByUser(setUsername()).observe(this, this::updateList);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void updateList(List<Note>notes){
        List<Note> aux=new ArrayList<>();
        if(resctriction!=-1){
            for(Note x: notes){
                if(x.getCategoryID()==resctriction){
                    aux.add(x);
                }
            }
        }
        System.out.println(aux.size()+"tamaño");
        adapter.setNotes(aux);
    }

    public void setResctriction(int resctriction){
        this.resctriction=resctriction;
    }

    private String setUsername(){
        SharedPreferences preferences=activity.getSharedPreferences("log", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.login_token), "");
    }

}
