package com.kilomobi.bbfoot;

import android.content.res.AssetFileDescriptor;

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
    private String mImagePath;
    private PlayerAdapter listAdapter;
    // State 1 = RED
    // State 2 = Blue
    private int state;

    private Singleton(){
        mString = "Hello";
        state = 1;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}