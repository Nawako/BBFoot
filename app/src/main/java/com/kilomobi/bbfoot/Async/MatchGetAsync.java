package com.kilomobi.bbfoot.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.kilomobi.bbfoot.Global.Globale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

// ===========================================================
// Async qui récupère la liste des joueurs de la BDD
// ===========================================================
public class MatchGetAsync extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Activity mActivity;
    Context mContext;
    String response;

    public String getResponse() {
        return response;
    }

    public MatchGetAsync(Activity activity, Context context) {
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
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {
        System.setProperty("http.keepAlive", "false");
        response = "";
        try {
            //Appel du webservice
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(Globale.webURL+Globale.MATCH);

            // Envoi de la requête GET
            HttpResponse httpResponse = httpClient.execute(request);
            response = EntityUtils.toString(httpResponse.getEntity());

            Log.v("Get_Match : ", response);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.dismiss();
    }
}
