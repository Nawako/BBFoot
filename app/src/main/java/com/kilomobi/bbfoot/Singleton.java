package com.kilomobi.bbfoot;

import com.kilomobi.bbfoot.Async.OnTaskCompletedInterface;
import com.kilomobi.bbfoot.Async.PlayerGetAsync;
import com.kilomobi.bbfoot.Controller.PlayerAdapter;
import com.kilomobi.bbfoot.Model.Player;

import java.util.ArrayList;

/**
 * Created by macbookpro on 29/10/2015.
 */
public class Singleton implements OnTaskCompletedInterface {
    private static Singleton mInstance = null;

    private String mString;
    private PlayerAdapter listAdapter;

    private Singleton(){
        mString = "Hello";
    }

    public PlayerAdapter getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(PlayerAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public String getString(){
        return this.mString;
    }

    public void setString(String value){
        mString = value;
    }

    @Override
    public ArrayList<Player> onTaskCompleted(String output) {
        return null;
    }

    @Override
    public void bindDataSet() {

    }
}