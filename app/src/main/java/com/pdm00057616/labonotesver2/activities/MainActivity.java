package com.pdm00057616.labonotesver2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.fragments.AddNoteFragment;
import com.pdm00057616.labonotesver2.fragments.NotesFragment;
import com.pdm00057616.labonotesver2.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
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
                    }
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.main_content, setFragment(type)).
                            commit();
                    drawerLayout.closeDrawers();
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
        }
        return null;
    }
}
