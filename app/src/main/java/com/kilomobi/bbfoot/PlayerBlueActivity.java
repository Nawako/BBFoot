package com.kilomobi.bbfoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;
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
public class PlayerBlueActivity extends PlayerActivity {

    private ListView lv_Player;
    private PlayerAdapter listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Singleton.getInstance().getListAdapter() != null) {
            setContentView(R.layout.activity_player_chooser_blue);

            lv_Player = (ListView) findViewById(R.id.activity_player_chooser_blue_lv);
            // sample demonstrate how to morph button to green circle with icon
            final MorphingButton btnMorph = (MorphingButton) findViewById(R.id.activity_player_chooser_blue_btn_valider);

            btnMorph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            lv_Player.setAdapter(Singleton.getInstance().getListAdapter());
            lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            lv_Player.setClickable(true);

        }
    }

    @Override
    public void bindDataSet() {
        Log.v("Access from : ", "child");
        lv_Player.setAdapter(Singleton.getInstance().getListAdapter());
        lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv_Player.setClickable(true);
    }
}
