package com.pdm00057616.labonotesver2.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.pdm00057616.labonotesver2.adapters.ViewPagerAdapter;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.viewmodels.CategoryViewModel;
import com.pdm00057616.labonotesver2.viewmodels.NoteViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotesFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private CategoryViewModel categoryViewModel;
    private Context context;
    private Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        activity=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_layout, container, false);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        categoryViewModel=ViewModelProviders.of(this).get(CategoryViewModel.class);
        adapter=new ViewPagerAdapter(getChildFragmentManager());
        categoryViewModel.getAllCategory().observe(this, this::addFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void addFragments(List<Category> categories){
        System.out.println(categories.size());
        for(int i=0; i<categories.size(); i++){
            NotesByCategoryFragment fragment=new NotesByCategoryFragment();
            fragment.setResctriction(i+1);
            adapter.addFragment(fragment, categories.get(i).getName());
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        System.out.println(adapter.getCount()+"cuenta");
    }



    private String setUsername(){
        SharedPreferences preferences=activity.getSharedPreferences("log", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.login_token), "");
    }
}
