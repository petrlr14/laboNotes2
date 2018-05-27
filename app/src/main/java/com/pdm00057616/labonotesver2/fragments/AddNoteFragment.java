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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.models.Category;
import com.pdm00057616.labonotesver2.models.Note;
import com.pdm00057616.labonotesver2.viewmodels.CategoryViewModel;
import com.pdm00057616.labonotesver2.viewmodels.NoteViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNoteFragment extends Fragment {

    private EditText editTextNote, editTextName;
    private Spinner spinner;
    private Button button;
    private Activity activity;
    private Context context;
    private ArrayAdapter<String> spinnerAdapter;
    private CategoryViewModel categoryViewModel;
    private NoteViewModel noteViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        activity = getActivity();
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
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().
                observe(this, categoryList -> {
                    spinnerAdapter = new ArrayAdapter<>
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
            System.out.println(spinner.getSelectedItemPosition());
            noteViewModel.insert(new Note(note, title, getUsername(), spinner.getSelectedItemPosition() + 1));
            Toast.makeText(context, "Note added!", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
            editTextNote.setText("");
        } else {
            Toast.makeText(context, "no no no", Toast.LENGTH_SHORT).show();
        }
    }

    private String[] getData(List<Category> categories) {
        if (categories != null && categories.size() != 0) {
            int size = categories.size();
            List<String> aux = new ArrayList<>();
            for (Category x : categories) {
                System.out.println(x.getId());
                aux.add(x.getName());
            }
            return Arrays.copyOf(aux.toArray(), size, String[].class);
        } else {
            String[] data = new String[1];
            data[0] = "Default";
            return data;
        }
    }

    private String getUsername() {
        SharedPreferences preferences = activity.getSharedPreferences("log", Context.MODE_PRIVATE);
        return preferences.getString(getString(R.string.login_token), "");
    }

}
