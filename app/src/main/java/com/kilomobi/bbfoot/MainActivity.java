package com.kilomobi.bbfoot;

import android.content.Intent;
import android.os.Bundle;
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

import com.kilomobi.bbfoot.Model.Player;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    TextView tvPrenomRed1;
    TextView tvPrenomRed2;
    TextView tvPrenomBlue1;
    TextView tvPrenomBlue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_match);

        tvScoreBlue = (TextView) findViewById(R.id.activity_main_tv_score_blue);
        tvScoreRed = (TextView) findViewById(R.id.activity_main_tv_score_red);
        tvPrenomRed1 = (TextView) findViewById(R.id.activity_main_tv_red_joueur1);
        tvPrenomRed2 = (TextView) findViewById(R.id.activity_main_tv_red_joueur2);
        tvPrenomBlue1 = (TextView) findViewById(R.id.activity_main_tv_blue_joueur1);
        tvPrenomBlue2 = (TextView) findViewById(R.id.activity_main_tv_blue_joueur2);

        // ===========================================================
        // Gestion des clicks sur les boutons
        // ===========================================================
        List<CircleImageView> redImages = new ArrayList<>();
        List<CircleImageView> blueImages = new ArrayList<>();
        List<TextView> redTexts = new ArrayList<>();
        List<TextView> blueTexts = new ArrayList<>();
        redTexts.add(tvPrenomRed1);
        redTexts.add(tvPrenomRed2);
        blueTexts.add(tvPrenomBlue1);
        blueTexts.add(tvPrenomBlue2);

        CircleImageView add_player_red1;
        add_player_red1 = (CircleImageView) findViewById(R.id.activity_main_ci_red_profile1);
        add_player_red1.setOnClickListener(this);
        add_player_red1.setOnLongClickListener(this);
        redImages.add(add_player_red1);

        CircleImageView add_player_red2;
        add_player_red2 = (CircleImageView) findViewById(R.id.activity_main_ci_red_profile2);
        add_player_red2.setOnClickListener(this);
        add_player_red2.setOnLongClickListener(this);
        redImages.add(add_player_red2);

        CircleImageView add_player_blue1;
        add_player_blue1 = (CircleImageView) findViewById(R.id.activity_main_ci_blue_profile1);
        add_player_blue1.setOnClickListener(this);
        add_player_blue1.setOnLongClickListener(this);
        blueImages.add(add_player_blue1);

        CircleImageView add_player_blue2;
        add_player_blue2 = (CircleImageView) findViewById(R.id.activity_main_ci_blue_profile2);
        add_player_blue2.setOnClickListener(this);
        add_player_blue2.setOnLongClickListener(this);
        blueImages.add(add_player_blue2);

        // Gestion du joueur sur la bulle

        List<Player> redTeam = Singleton.getInstance().getListAdapter().getListOfRedPlayers();
        List<Player> blueTeam = Singleton.getInstance().getListAdapter().getListOfBluePlayers();

        setImageToCircleImage(redTeam, redImages);
        setImageToCircleImage(blueTeam, blueImages);
        setTextToTextview(redTeam, redTexts);
        setTextToTextview(blueTeam, blueTexts);

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

    private void setImageToCircleImage(List<Player> team, List<CircleImageView> teamImage) {
        String imagePath = Singleton.getInstance().getmImagePath();

        for (int i = 0; i < team.size(); i++) {
            Picasso.with(getApplicationContext())
                    .load(new File(imagePath + team.get(i).getImage()))
                    .placeholder(R.drawable.default_avatar)
                    .resize(200, 200)
                    .centerCrop()
                    .into(teamImage.get(i));
        }
    }

    private void setTextToTextview (List<Player> team, List<TextView> teamText) {
        for (int i = 0; i < team.size(); i++) {
            teamText.get(i).setText(team.get(i).getPrenom());
        }
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
            intent.setClass(this, PlayerRedActivity.class);
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
