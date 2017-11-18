package com.alexblogg.djehuti.activities;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alexblogg.djehuti.R;
import com.alexblogg.djehuti.database.DHTDataBase;
import com.alexblogg.djehuti.fragments.FieldFragment;
import com.alexblogg.djehuti.fragments.HomeFragment;
import com.alexblogg.djehuti.fragments.NoteFragment;
import com.alexblogg.djehuti.fragments.SchedFragment;
import com.alexblogg.djehuti.fragments.TaskFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static DHTDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: 18/11/2017 Change implementation to behind the scenes / async
        db = Room.databaseBuilder(getApplicationContext(), DHTDataBase.class, "djehuti").allowMainThreadQueries().build();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment homeFrag = HomeFragment.newInstance("param1", "param2");
        TransitionFragment(homeFrag);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            HomeFragment homeFrag = HomeFragment.newInstance("param1", "param2");
            TransitionFragment(homeFrag);
        } else if (id == R.id.nav_task) {
            TaskFragment taskFrag = TaskFragment.newInstance("param1", "param2");
            TransitionFragment(taskFrag);
        } else if (id == R.id.nav_sched) {
            SchedFragment schedFrag = SchedFragment.newInstance("param1", "param2");
            TransitionFragment(schedFrag);
        } else if (id == R.id.nav_field) {
            FieldFragment fieldFrag = FieldFragment.newInstance("param1", "param2");
            TransitionFragment(fieldFrag);
        } else if (id == R.id.nav_note) {
            NoteFragment noteFrag = NoteFragment.newInstance();
            TransitionFragment(noteFrag);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void TransitionFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
