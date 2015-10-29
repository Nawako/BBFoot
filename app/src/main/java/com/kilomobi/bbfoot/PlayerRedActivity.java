package com.kilomobi.bbfoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

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
public class PlayerRedActivity extends AppCompatActivity {

    private ListView lv_Player;
    private PlayerAdapter listAdapter ;

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
                List<Integer> getListOfRed = listAdapter.getListOfSelectedPlayers();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), PlayerBlueActivity.class);
                startActivity(intent);
            }
        });

        // to retrieve object in second Activity
        getIntent().getSerializableExtra("MyClass");

        String response = "";
        try {
            PlayerGetAsync asyncGet = new PlayerGetAsync(this,this);
            asyncGet.execute().get();
            response = asyncGet.getResponse();
        }catch (Exception e) {e.printStackTrace();}

        /**************** Create Player Adapter *********/
        ArrayList<Player> playerList = new ArrayList<>();

        try {
            // Récupère le JSON
            JSONObject myObject = new JSONObject(response);

            JSONArray m_jArry = myObject.getJSONArray("Players");
            int j_totalSize = m_jArry.length();
            for (int i = 0; i<j_totalSize; i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                // Récupère les params
                int jo_id = jo_inside.getInt("PlayerId");
                String jo_nom = jo_inside.getString("Nom");
                String jo_prenom = jo_inside.getString("Prenom");
                String jo_image = jo_inside.getString("ImageId");

                // Assignation à un joueur
                Player p = new Player();
                p.set_id(jo_id);
                p.setPrenom(jo_prenom);
                p.setNom(jo_nom);
                p.setImage(jo_image);
                playerList.add(p);

                Log.v("Get : ", jo_nom + " " + jo_prenom + " " + jo_image);
            }
        } catch (JSONException o) {o.printStackTrace();}

        listAdapter = new PlayerAdapter(this, playerList);
        lv_Player.setAdapter(listAdapter);
        lv_Player.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        lv_Player.setClickable(true);
    }
}
