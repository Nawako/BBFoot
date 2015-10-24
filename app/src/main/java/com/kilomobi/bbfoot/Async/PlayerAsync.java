package com.kilomobi.bbfoot.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Nawako on 24/10/2015.
 */
public class PlayerAsync extends AsyncTask {

    ProgressDialog dialog;
    Activity mActivity;
    Context mContext;

    public PlayerAsync(Activity activity, Context context) {
        mActivity = activity;
        mContext = context;
        dialog = new ProgressDialog(activity);
        String str = "Connexion en cours";
        dialog.setTitle(str + "...");
        dialog.setIndeterminate(false);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
}
