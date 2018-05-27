package com.pdm00057616.labonotesver2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.models.Category;

public class AddCategoryFragment extends Fragment {

    private Context context;
    private EditText editTextCategoryName;
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_category_layout, container, false);
        editTextCategoryName=view.findViewById(R.id.text_view_category_name);
        button=view.findViewById(R.id.button_done);
        button.setOnClickListener((v)->setButtonAction());
        return view;
    }

    private void setButtonAction(){
        String name=editTextCategoryName.getText().toString();
        if(!name.equals("")){
            Category category=new Category(name, "");
            editTextCategoryName.setText("");
        }
    }
}
