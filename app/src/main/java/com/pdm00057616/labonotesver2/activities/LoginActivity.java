package com.pdm00057616.labonotesver2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.database.DB;
import com.pdm00057616.labonotesver2.database.daos.UserDao;
import com.pdm00057616.labonotesver2.database.repositories.UserRepository;
import com.pdm00057616.labonotesver2.models.User;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText editTextUsername;
    private UserRepository userRepository;

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
        User user=userRepository.getUserByUsername(editTextUsername.getText().toString());
        if(user==null){
            userRepository.insert(new User(username, 0));
        }
        createSP();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void createSP(){
        SharedPreferences preferences=this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(getString(R.string.login_token), editTextUsername.getText().toString());
        editor.apply();
    }
}
