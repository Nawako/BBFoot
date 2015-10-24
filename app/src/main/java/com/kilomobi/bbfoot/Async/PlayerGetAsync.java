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

public class PlayerGetAsync extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Activity mActivity;
    Context mContext;

    public PlayerGetAsync(Activity activity, Context context) {
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
        String response = "";
        try {
            //Appel du webservice
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(Globale.webURL+Globale.GET);

            // Envoi de la requête GET
            HttpResponse httpResponse = httpClient.execute(request);

            // TODO enlever le traitement JSON de la méthode et le traiter dans une classe à part
            // Récupère le JSON
            JSONObject myObject = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));

            JSONArray m_jArry = myObject.getJSONArray("Players");
            int j_totalSize = m_jArry.length();
            for (int i = 0; i<j_totalSize; i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                // Récupère les params
                int jo_id = jo_inside.getInt("PlayerId");
                String jo_nom = jo_inside.getString("Nom");
                String jo_prenom = jo_inside.getString("Prenom");

                Log.v("Get : ", jo_nom + " " + jo_prenom);
            }

            Log.v("Get : ", response);

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
