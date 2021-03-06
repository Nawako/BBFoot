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
// Async qui permet d'ajouter un joueur à la BDD
// ===========================================================
public class MatchAddAsync extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Activity mActivity;
    Context mContext;

    public MatchAddAsync(Activity activity, Context context) {
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
            HttpPost request = new HttpPost(Globale.webURL+Globale.MATCH+Globale.CREATE);

            //Prepare les params
            List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("MatchId", params[0]));
            postParameters.add(new BasicNameValuePair("Rouge1Id", params[1]));
            postParameters.add(new BasicNameValuePair("Rouge2Id", params[2]));
            postParameters.add(new BasicNameValuePair("Bleu1Id", params[3]));
            postParameters.add(new BasicNameValuePair("Bleu2Id", params[4]));
            postParameters.add(new BasicNameValuePair("date", params[5]));

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);

            // Execute la requête POST
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(request, responseHandler);

            Log.v("Post : ", response);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.dismiss();
        mActivity.finish();
    }
}