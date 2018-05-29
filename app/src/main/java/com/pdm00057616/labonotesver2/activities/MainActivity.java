package com.pdm00057616.labonotesver2.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.database.repositories.UserRepository;
import com.pdm00057616.labonotesver2.fragments.AddCategoryFragment;
import com.pdm00057616.labonotesver2.fragments.AddNoteFragment;
import com.pdm00057616.labonotesver2.fragments.NotesFragment;
import com.pdm00057616.labonotesver2.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private SharedPreferences preferences;
    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=this.getSharedPreferences("log", Context.MODE_PRIVATE);
        isLogged();
        bindViews();
        setFirstView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener((item) -> {
                    Fragment fragment;
                    int type = -1;
                    switch (item.getItemId()) {
                        case R.id.account_section:
                            type = 0;
                            break;
                        case R.id.notes_section:
                            type = 1;
                            break;
                        case R.id.add_note_section:
                            type=2;
                            break;
                        case R.id.add_category_section:
                            type=3;
                            break;
                        case R.id.logout_section:
                            logout();
                            drawerLayout.closeDrawers();
                            return true;
                    }
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_content, setFragment(type)).
                            commit();
                    drawerLayout.closeDrawers();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    return true;
                }
        );

    }

    private Fragment setFragment(int type) {
        switch (type) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new NotesFragment();
            case 2:
                return new AddNoteFragment();
            case 3:
                return new AddCategoryFragment();
        }
        return null;
    }

    private void logout(){
        SharedPreferences preferences=this.getSharedPreferences("log", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void isLogged(){
        if(!preferences.contains(getString(R.string.login_token))){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    private void setFirstView(){
        navigationView.getMenu().getItem(0).setChecked(true);
        Fragment fragment = new ProfileFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();
        getSupportActionBar().setTitle(navigationView.getMenu().getItem(0).getTitle());
    }
}
