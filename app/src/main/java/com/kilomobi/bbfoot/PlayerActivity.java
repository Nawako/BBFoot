package com.kilomobi.bbfoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
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

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerActivity extends AppCompatActivity {

    private ListView lv_Player;
    private PlayerAdapter listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_chooser);

        lv_Player = (ListView) findViewById(R.id.activity_player_chooser_lv);
        // sample demonstrate how to morph button to green circle with icon
        final MorphingButton btnMorph = (MorphingButton) findViewById(R.id.activity_chooser_btn_valider);

        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // inside on click event
//                MorphingButton.Params circle = MorphingButton.Params.create()
//                        .duration(500)
//                        .cornerRadius(56) // 56 dp
//                        .width(56) // 56 dp
//                        .height(56) // 56 dp
//                        .color(android.R.color.holo_green_light) // normal state color
//                        .colorPressed(android.R.color.holo_green_dark) // pressed state color
//                        .icon(R.drawable.sample); // icon
//                btnMorph.morph(circle);
            }
        });

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
        lv_Player.setSelector(R.color.mb_blue);
        lv_Player.setClickable(true);
        lv_Player.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          //      if (view.getDrawingCacheBackgroundColor() == getResources().getColor(R.color.colorPrimary))
                view.setBackgroundResource(R.color.colorAccent);
                Log.v("ItemClick pos : ", String.valueOf(i));
                Log.v("ItemClick count : ", String.valueOf(lv_Player.getCheckedItemCount()));
                SparseBooleanArray spb = lv_Player.getCheckedItemPositions();
                view.setSelected(true);
            }
        });
    }
}
