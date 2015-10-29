package com.kilomobi.bbfoot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dd.morphingbutton.MorphingButton;
import com.kilomobi.bbfoot.Async.OnTaskCompletedInterface;
import com.kilomobi.bbfoot.Async.PlayerGetAsync;
import com.kilomobi.bbfoot.Controller.PlayerAdapter;
import com.kilomobi.bbfoot.Model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerRedActivity extends PlayerActivity implements OnTaskCompletedInterface {

    private ListView lv_Player;
    private PlayerAdapter la_Player;
    private Activity mActivity;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser_red);
        lv_Player = (ListView) findViewById(R.id.activity_player_chooser_red_lv);
        // sample demonstrate how to morph button to green circle with icon
        final MorphingButton btnMorph = (MorphingButton) findViewById(R.id.activity_player_chooser_red_btn_valider);

        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), PlayerBlueActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public ArrayList<Player> onTaskCompleted(String output) {
        return super.onTaskCompleted(output);
    }

    @Override
    public void bindDataSet() {
        Log.v("Access from : ", "child");
        lv_Player.setAdapter(getListAdapter());
        lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv_Player.setClickable(true);
    }

    @Override
    public PlayerAdapter getListAdapter() {
        return super.getListAdapter();
    }
}