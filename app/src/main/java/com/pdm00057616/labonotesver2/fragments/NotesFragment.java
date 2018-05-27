package com.pdm00057616.labonotesver2.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.adapters.NotesRecyclerView;
import com.pdm00057616.labonotesver2.viewmodels.NoteViewModel;

public class NotesFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private NoteViewModel noteViewModel;
    private NotesRecyclerView adapter;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_layout, container, false);
        /*tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);*/
        adapter=new NotesRecyclerView();
        noteViewModel= ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNotesByUser("").observe(this, adapter::setNotes);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

}
