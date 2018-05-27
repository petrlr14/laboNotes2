package com.pdm00057616.labonotesver2.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNoteFragment extends Fragment {

    private EditText editTextNote, editTextName;
    private Spinner spinner;
    private Button button;
    private Context context;
    private List<Category> categories;
    private String[] data;
    private ArrayAdapter<String> spinnerAdapter;
    private CategoryViewModel categoryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_note_layout, container, false);
        editTextNote = view.findViewById(R.id.edit_text_note);
        editTextName = view.findViewById(R.id.edit_text_title);
        button = view.findViewById(R.id.button_done);
        button.setOnClickListener(v -> buttonAction());
        spinner = view.findViewById(R.id.spinner_);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().
                observe(this, categoryList -> {
                    spinnerAdapter= new ArrayAdapter<>
                            (this.context, android.R.layout.simple_spinner_dropdown_item, getData(categoryList));
                    spinnerAdapter.notifyDataSetChanged();
                    spinner.setAdapter(spinnerAdapter);
                });
        return view;
    }

    private void buttonAction() {
        String note = editTextNote.getText().toString();
        if (!note.equals("")) {
            String title = editTextName.getText().equals("") ?
                    "No title" : editTextName.getText().toString();
            Toast.makeText(context, "holi", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
        } else {
            Toast.makeText(context, "no no no", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] getData(List<Category> categories){
        if(categories!=null&&categories.size()!=0){
            int size=categories.size();
            String[] data=new String[size];
            List<String> aux=new ArrayList<>();
            for(Category x:categories){
                aux.add(x.getName());
            }
            return Arrays.copyOf(aux.toArray(), size, String[].class);
        }else{
            String[] data=new String[1];
            data[0]="Default";
            return data;
        }
    }


}
