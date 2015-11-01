package com.kilomobi.bbfoot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.dd.morphingbutton.MorphingButton;
import com.kilomobi.bbfoot.Async.OnTaskCompletedInterface;
import com.kilomobi.bbfoot.Controller.PlayerAdapter;
import com.kilomobi.bbfoot.Model.Player;

import java.util.ArrayList;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerRedFragment extends PlayerFragment implements OnTaskCompletedInterface {

    private ListView lv_Player;
    private PlayerAdapter la_Player;
    private Activity mActivity;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_player_chooser_red, null);
        lv_Player = (ListView) rootView.findViewById(R.id.activity_player_chooser_red_lv);
        // sample demonstrate how to morph button to green circle with icon
        final MorphingButton btnMorph = (MorphingButton) rootView.findViewById(R.id.activity_player_chooser_red_btn_valider);

        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().setState(2);
                Singleton.getInstance().getListAdapter()
                        .setListOfRedPlayers(Singleton.getInstance()
                                .getListAdapter()
                                .getListOfSelectedPlayersAsPlayer());
                Intent intent = new Intent();
                intent.setClass(getActivity(), PlayerBlueFragment.class);
                startActivity(intent);
            }
        });
        return rootView;
    }


    @Override
    public ArrayList<Player> onTaskCompleted(String output) {
        return super.onTaskCompleted(output);
    }

    @Override
    public void bindDataSet() {
        Log.v("Access from : ", "child");
        Singleton.getInstance().setListAdapter(listAdapter);
        lv_Player.setAdapter(Singleton.getInstance().getListAdapter());
        lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv_Player.setClickable(true);
    }
}