package com.kilomobi.bbfoot;

import android.widget.ListAdapter;

import com.kilomobi.bbfoot.Controller.PlayerAdapter;
import com.kilomobi.bbfoot.Model.Player;
import java.util.ArrayList;

/**
 * Created by macbookpro on 29/10/2015.
 */
public interface PlayerHolder {
    PlayerAdapter listAdapter = null;
    ArrayList<Player> onTaskCompleted(String output);
    void bindDataSet();
}