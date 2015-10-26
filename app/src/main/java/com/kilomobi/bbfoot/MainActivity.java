package com.kilomobi.bbfoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int scoreRed = 0;
    int scoreBlue = 0;
    TextView tvScoreRed;
    TextView tvScoreBleu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvScoreBleu = (TextView)findViewById(R.id.activity_main_tv_score_blue);
        tvScoreRed = (TextView)findViewById(R.id.activity_main_tv_score_red);

        FloatingActionButton addRed;
        addRed = (FloatingActionButton) findViewById(R.id.activity_main_red_add);
        addRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreRed++;
                tvScoreRed.setText(scoreRed);
            }
        });

        FloatingActionButton minusRed;
        minusRed = (FloatingActionButton) findViewById(R.id.activity_main_red_remove);
        minusRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreRed--;
                tvScoreRed.setText(scoreRed);
            }
        });

        FloatingActionButton addBlue;
        addBlue = (FloatingActionButton) findViewById(R.id.activity_main_bleu_add);
        addBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreBlue++;
                tvScoreBleu.setText(scoreBlue);
            }
        });

        FloatingActionButton minusBlue;
        minusBlue = (FloatingActionButton) findViewById(R.id.activity_main_bleu_remove);
        minusBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreBlue--;
                tvScoreBleu.setText(scoreBlue);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        // Async
//        PlayerAddAsync asyncAdd = new PlayerAddAsync(this, this);
//        // Params pour add un joueur
//        asyncAdd.execute("1", "Android", "EnvoyeDepuisApp");

//        PlayerGetAsync asyncGet = new PlayerGetAsync(this,this);
//        asyncGet.execute();

//        PlayerDeleteAsync asyncDelete = new PlayerDeleteAsync(this, this);
//        asyncDelete.execute("4");

//        PlayerModifyAsync asyncModify = new PlayerModifyAsync(this, this);
//        asyncModify.execute("6", "Fabrisse", "Modification");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_newmatch) {
            // TODO créer un nouveau match, choisir joueurs
        } else if (id == R.id.nav_player) {
            // TODO faire les stats des joueurs
            Intent intent = new Intent();
            intent.setClass(this, PlayerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fun) {
            // TODO faire les funny stufs comme le son
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
