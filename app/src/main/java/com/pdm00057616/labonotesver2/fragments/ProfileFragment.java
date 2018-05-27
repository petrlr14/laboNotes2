package com.pdm00057616.labonotesver2.fragments;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.database.repositories.UserRepository;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.viewmodels.CategoryViewModel;
import com.pdm00057616.labonotesver2.viewmodels.NoteViewModel;
import com.pdm00057616.labonotesver2.viewmodels.UserViewModel;

import java.util.List;

public class ProfileFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private NoteViewModel noteViewModel;
    private UserViewModel userViewModel;
    private ImageView imageView;
    private TextView textViewNoteCount, textViewCategoryCount, textViewUsername;
    private Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        imageView = view.findViewById(R.id.image_view_account);
        textViewNoteCount = view.findViewById(R.id.notes_count);
        textViewCategoryCount = view.findViewById(R.id.category_count);
        textViewUsername=view.findViewById(R.id.text_view_username);
        textViewUsername.setText(getUsername());
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNotesByUser(getUsername()).observe(this, (notes) -> {
            String aux = notes == null ? "0" : notes.size() + "";
            textViewNoteCount.setText(aux);
        });
        categoryViewModel.getAllCategory().observe(this, (categories) -> {
            String aux = categories == null ? "0" : categories.size() + "";
            textViewCategoryCount.setText(aux);
        });
        return view;
    }

    private String getUsername(){
        SharedPreferences preferences=activity.getSharedPreferences("log", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.login_token), "");
    }

}
