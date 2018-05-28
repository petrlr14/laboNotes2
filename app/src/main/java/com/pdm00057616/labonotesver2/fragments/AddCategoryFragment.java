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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.viewmodels.CategoryViewModel;

public class AddCategoryFragment extends Fragment {

    private Context context;
    private Activity activity;
    private EditText editTextCategoryName;
    private Button button;
    private CategoryViewModel categoryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=getActivity();
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_category_layout, container, false);
        editTextCategoryName=view.findViewById(R.id.text_view_category_name);
        categoryViewModel= ViewModelProviders.of(this).get(CategoryViewModel.class);
        button=view.findViewById(R.id.button_done);
        button.setOnClickListener((v)->setButtonAction());
        return view;
    }

    private void setButtonAction(){
        String name=editTextCategoryName.getText().toString();
        if(!name.equals("")){
            categoryViewModel.insert(new Category(name, getUser()));
            editTextCategoryName.setText("");
            Toast.makeText(context, "Category added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private String getUser(){
        SharedPreferences preferences=activity.getSharedPreferences("log", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.login_token), "");
    }
}
