package com.kilomobi.bbfoot;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.kilomobi.bbfoot.Async.OnTaskCompletedInterface;
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
public class PlayerFragment extends Fragment implements OnTaskCompletedInterface {

    public PlayerAdapter listAdapter ;
    private OnTaskCompletedInterface listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener = this;
        if (Singleton.getInstance().getListAdapter() == null) {
            try {
                PlayerGetAsync asyncGet = new PlayerGetAsync(super.getActivity(), this);
                asyncGet.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Player> onTaskCompleted(String output) {
        // Créer l'adapter joueur
        ArrayList<Player> playerList = new ArrayList<>();

        try {
            // Récupère le JSON
            JSONObject myObject = new JSONObject(output);

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
            listAdapter = new PlayerAdapter(super.getActivity(), playerList);
        } catch (JSONException o) {o.printStackTrace();}
        listener.bindDataSet();
        return playerList;
    }

    @Override
    public void bindDataSet() {
        Log.v("Access from : ", "superclass");
    }
}
