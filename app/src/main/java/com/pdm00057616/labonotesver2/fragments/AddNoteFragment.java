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
import android.widget.Spinner;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;

public class AddNoteFragment extends Fragment {

    private EditText editTextNote, editTextName;
    private Spinner spinner;
    private Button button;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_note_layout, container, false);
        editTextNote=view.findViewById(R.id.edit_text_note);
        editTextName=view.findViewById(R.id.edit_text_title);
        button=view.findViewById(R.id.button_done);
        button.setOnClickListener(v->buttonAction());
        spinner=view.findViewById(R.id.spinner_);
        return view;
    }

    private void buttonAction(){
        String note= editTextNote.getText().toString();
        if(!note.equals("")){
            String title=editTextName.getText().equals("")?
                    "No title":editTextName.getText().toString();
            Toast.makeText(context, "holi", Toast.LENGTH_SHORT).show();
            editTextName.setText("");
        }else{
            Toast.makeText(context, "no no no", Toast.LENGTH_SHORT).show();
        }
    }

}
