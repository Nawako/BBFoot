package com.kilomobi.bbfoot.Async;

import com.kilomobi.bbfoot.Model.Player;

import java.util.ArrayList;

/**
 * Created by macbookpro on 29/10/2015.
 */
public interface OnTaskCompletedInterface {
    ArrayList<Player> onTaskCompleted(String output);
    void bindDataSet();
}