package com.kilomobi.bbfoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dd.morphingbutton.MorphingButton;
import com.kilomobi.bbfoot.Controller.PlayerController;
import com.kilomobi.bbfoot.Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nawako on 22/10/2015.
 */
public class PlayerActivity extends AppCompatActivity {

    private ListView lv_Player;
    private PlayerController listAdapter ;

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
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(500)
                        .cornerRadius(56) // 56 dp
                        .width(56) // 56 dp
                        .height(56) // 56 dp
                        .color(android.R.color.holo_green_light) // normal state color
                        .colorPressed(android.R.color.holo_green_dark) // pressed state color
                        .icon(R.drawable.sample); // icon
                btnMorph.morph(circle);
            }
        });

        /**************** Create Player Adapter *********/
        ArrayList<Player> playerList = new ArrayList<>();

        Player p = new Player();
        p.setPrenom("Pierre");
        p.setNom("P");
        playerList.add(p);

        Player p1 = new Player();
        p1.setPrenom("André");
        p1.setNom("W");
        playerList.add(p1);

        Player p2 = new Player();
        p2.setPrenom("Yannick");
        p2.setNom("K");
        playerList.add(p2);

        listAdapter = new PlayerController(getApplicationContext(), playerList);
        lv_Player.setAdapter( listAdapter );
    }

}
