package com.pdm00057616.labonotesver2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;

import java.util.ArrayList;

public class NoteViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String title, category, note;
    private TextView textViewCategory, textViewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        getData();
        init();
    }

    private void getData(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ArrayList<String> aux=new ArrayList<>();
        aux=intent.getStringArrayListExtra("data");
        title=aux.get(0);
        category=aux.get(1);
        note=aux.get(2);
    }

    private void init(){
        textViewCategory=findViewById(R.id.category);
        textViewNote=findViewById(R.id.text_);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        textViewCategory.setText(category);
        textViewNote.setText(note);
    }
}
