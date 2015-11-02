package com.kilomobi.bbfoot;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kilomobi.bbfoot.Async.MatchAddAsync;
import com.kilomobi.bbfoot.Model.Player;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MatchFragment extends Fragment implements
        View.OnClickListener, View.OnLongClickListener {

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
    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_match, null);

        tvScoreBlue = (TextView) rootView.findViewById(R.id.activity_main_tv_score_blue);
        tvScoreRed = (TextView) rootView.findViewById(R.id.activity_main_tv_score_red);
        tvPrenomRed1 = (TextView) rootView.findViewById(R.id.activity_main_tv_red_joueur1);
        tvPrenomRed2 = (TextView) rootView.findViewById(R.id.activity_main_tv_red_joueur2);
        tvPrenomBlue1 = (TextView) rootView.findViewById(R.id.activity_main_tv_blue_joueur1);
        tvPrenomBlue2 = (TextView) rootView.findViewById(R.id.activity_main_tv_blue_joueur2);
        ImageView btnChinois = (ImageView) rootView.findViewById(R.id.activity_main_match_btnChinois);
        btnChinois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchAddAsync asyncAdd = new MatchAddAsync(getActivity(), getActivity());
//        // Params pour add un joueur
        asyncAdd.execute("1", "5", "6", "8", "9");
            }
        });

        drawer = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);

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
        add_player_red1 = (CircleImageView) rootView.findViewById(R.id.activity_main_ci_red_profile1);
        add_player_red1.setOnClickListener(this);
        add_player_red1.setOnLongClickListener(this);
        redImages.add(add_player_red1);

        CircleImageView add_player_red2;
        add_player_red2 = (CircleImageView) rootView.findViewById(R.id.activity_main_ci_red_profile2);
        add_player_red2.setOnClickListener(this);
        add_player_red2.setOnLongClickListener(this);
        redImages.add(add_player_red2);

        CircleImageView add_player_blue1;
        add_player_blue1 = (CircleImageView) rootView.findViewById(R.id.activity_main_ci_blue_profile1);
        add_player_blue1.setOnClickListener(this);
        add_player_blue1.setOnLongClickListener(this);
        blueImages.add(add_player_blue1);

        CircleImageView add_player_blue2;
        add_player_blue2 = (CircleImageView) rootView.findViewById(R.id.activity_main_ci_blue_profile2);
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
        return rootView;
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
            Picasso.with(getActivity())
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
}
