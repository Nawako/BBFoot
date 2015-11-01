package com.kilomobi.bbfoot;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by macbookpro on 30/10/2015.
 */
public class NavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Fragment fragment;
    SoundActivity soundActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        soundActivity = new SoundActivity();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment = new HomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_newmatch) {
            // TODO créer un nouveau match, choisir joueurs
            fragment = new PlayerRedFragment();
            FragmentManager fragmentManager = getFragmentManager();
            android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).commit();
        } else if (id == R.id.nav_player) {
            // TODO faire les stats des joueurs
            fragment = new PlayerAddFragment();
            FragmentManager fragmentManager = getFragmentManager();
            android.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, fragment).commit();
        } else if (id == R.id.nav_fun) {
            // TODO faire les funny stufs comme le son
            new MaterialDialog.Builder(this)
                    .title("Manque d'animation ?")
                    .items(R.array.mp3)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            soundActivity.stopPlaying();
                            soundActivity.openSound(getApplicationContext(), text + ".mp3");
                        }
                    })
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new MaterialDialog.Builder(this)
                    .title("Quitter")
                    .content("Êtes-vous sûr de vouloir quitter l'application ?")
                    .positiveText("Oui")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            finish();
                        }
                    })
                    .negativeText("Non")
                    .show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        soundActivity.stop();
    }
}
