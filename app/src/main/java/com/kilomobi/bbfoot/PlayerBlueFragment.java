package com.kilomobi.bbfoot;

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
import com.kilomobi.bbfoot.Controller.PlayerAdapter;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerBlueFragment extends PlayerFragment {

    private ListView lv_Player;
    private PlayerAdapter listAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_player_chooser_blue, null);

        if (Singleton.getInstance().getListAdapter() != null) {

            lv_Player = (ListView) rootView.findViewById(R.id.activity_player_chooser_blue_lv);
            // sample demonstrate how to morph button to green circle with icon
            final MorphingButton btnMorph = (MorphingButton) rootView.findViewById(R.id.activity_player_chooser_blue_btn_valider);

            btnMorph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton.getInstance().setState(2);
                    Singleton.getInstance().getListAdapter()
                            .setListOfBluePlayers(Singleton.getInstance()
                                    .getListAdapter()
                                    .getListOfSelectedPlayersAsPlayer());
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });

            lv_Player.setAdapter(Singleton.getInstance().getListAdapter());
            lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            lv_Player.setClickable(true);

        }

        return rootView;
    }

    @Override
    public void bindDataSet() {
        Log.v("Access from : ", "child");
        lv_Player.setAdapter(Singleton.getInstance().getListAdapter());
        lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv_Player.setClickable(true);
    }
}
