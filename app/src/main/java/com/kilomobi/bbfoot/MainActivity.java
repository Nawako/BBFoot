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

import com.kilomobi.bbfoot.Async.PlayerGetAsync;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, View.OnLongClickListener {

    int scoreRed1 = 0;
    int scoreRed2 = 0;
    int scoreRed = 0;
    int scoreBlue1 = 0;
    int scoreBlue2 = 0;
    int scoreBlue = 0;
    TextView tvScoreRed;
    TextView tvScoreBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvScoreBlue = (TextView)findViewById(R.id.activity_main_tv_score_blue);
        tvScoreRed = (TextView)findViewById(R.id.activity_main_tv_score_red);

        // ===========================================================
        // Gestion des clicks sur les boutons
        // ===========================================================
        CircleImageView add_player_red1;
        add_player_red1 = (CircleImageView) findViewById(R.id.activity_main_ci_red_profile1);
        add_player_red1.setOnClickListener(this);
        add_player_red1.setOnLongClickListener(this);

        CircleImageView add_player_red2;
        add_player_red2 = (CircleImageView) findViewById(R.id.activity_main_ci_red_profile2);
        add_player_red2.setOnClickListener(this);
        add_player_red2.setOnLongClickListener(this);

        CircleImageView add_player_blue1;
        add_player_blue1 = (CircleImageView) findViewById(R.id.activity_main_ci_blue_profile1);
        add_player_blue1.setOnClickListener(this);
        add_player_blue1.setOnLongClickListener(this);

        CircleImageView add_player_blue2;
        add_player_blue2 = (CircleImageView) findViewById(R.id.activity_main_ci_blue_profile2);
        add_player_blue2.setOnClickListener(this);
        add_player_blue2.setOnLongClickListener(this);

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
//        asyncGet.getResponse();

//        PlayerDeleteAsync asyncDelete = new PlayerDeleteAsync(this, this);
//        asyncDelete.execute("4");

//        PlayerModifyAsync asyncModify = new PlayerModifyAsync(this, this);
//        asyncModify.execute("6", "Fabrisse", "Modification");
    }

    // ===========================================================
    // Gestion du click sur le joueur, incrémente le score total
    // et individuel
    // ===========================================================
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_ci_red_profile1:
                scoreRed++;
                scoreRed1++;
                tvScoreRed.setText(String.valueOf(scoreRed));
                break;
            case R.id.activity_main_ci_red_profile2:
                scoreRed++;
                scoreRed2++;
                tvScoreRed.setText(String.valueOf(scoreRed));
                break;
            case R.id.activity_main_ci_blue_profile1:
                scoreBlue++;
                scoreBlue1++;
                tvScoreBlue.setText(String.valueOf(scoreBlue));
                break;
            case R.id.activity_main_ci_blue_profile2:
                scoreBlue++;
                scoreBlue2++;
                tvScoreBlue.setText(String.valueOf(scoreBlue));
                break;
        }
    }

    // ===========================================================
    // Gestion du long click sur le joueur, décrémente le score total
    // et individuel
    // ===========================================================
    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_ci_red_profile1:
                scoreRed--;
                scoreRed1--;
                tvScoreRed.setText(String.valueOf(scoreRed));
                break;
            case R.id.activity_main_ci_red_profile2:
                scoreRed--;
                scoreRed2--;
                tvScoreRed.setText(String.valueOf(scoreRed));
                break;
            case R.id.activity_main_ci_blue_profile1:
                scoreBlue--;
                scoreBlue1--;
                tvScoreBlue.setText(String.valueOf(scoreBlue));
                break;
            case R.id.activity_main_ci_blue_profile2:
                scoreBlue--;
                scoreBlue2--;
                tvScoreBlue.setText(String.valueOf(scoreBlue));
                break;
        }
        return true;
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
            Intent intent = new Intent();
            intent.setClass(this, PlayerActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_player) {
            // TODO faire les stats des joueurs
            Intent intent = new Intent();
            intent.setClass(this, PlayerAddActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fun) {
            // TODO faire les funny stufs comme le son

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
