package com.kilomobi.bbfoot.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.kilomobi.bbfoot.Global.Globale;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

// ===========================================================
// Async qui permet de modifier un joueur à la BDD
// ===========================================================
public class PlayerModifyAsync extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Activity mActivity;
    Context mContext;

    public PlayerModifyAsync(Activity activity, Context context) {
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
        // TODO Auto-generated method stub
        System.setProperty("http.keepAlive", "false");
        String response = "";
        try {
            //Appel du webservice
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(Globale.webURL+Globale.PLAYER+Globale.MODIFY);

            //Prepare les params
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("PlayerId", params[0]));
            postParameters.add(new BasicNameValuePair("Nom", params[1]));
            postParameters.add(new BasicNameValuePair("Prenom", params[2]));

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);

            // Execute la requête POST
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(request, responseHandler);

            Log.v("Post_Modify : ", response);

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