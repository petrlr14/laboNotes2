package com.pdm00057616.labonotesver2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.database.repositories.UserRepository;
import com.pdm00057616.labonotesver2.models.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText editTextUsername;
    private UserRepository userRepository;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        userRepository=new UserRepository(getApplication());
        editTextUsername=findViewById(R.id.edit_text_username);
        button=findViewById(R.id.button_enter);
        button.setOnClickListener(v->setButtonAction());
        userRepository.getAllUsers().observe(this, usersList->users=usersList);
    }

    private void setButtonAction(){
        if(!isEditTextUsernameEmpty()){
            log();
        }
    }

    private boolean isEditTextUsernameEmpty(){
        return (editTextUsername.getText().toString().equals(""));
    }

    private void log(){
        String username=editTextUsername.getText().toString();
        List<String>usernames=new ArrayList<>();
        if(users!=null){
            for(User x:users){
                usernames.add(x.getUsername());
            }
        }
        if(!usernames.contains(username)){
            userRepository.insert(new User(username, 0));
            Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
        }
        createSP();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void createSP(){
        SharedPreferences preferences=this.getSharedPreferences("log",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(getString(R.string.login_token), editTextUsername.getText().toString());
        editor.commit();
    }
}
