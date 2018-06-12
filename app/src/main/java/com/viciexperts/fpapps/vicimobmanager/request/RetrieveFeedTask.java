package com.viciexperts.fpapps.vicimobmanager.request;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private String API_URL = "";
    private String API_KEY;
    private CampaignRequest.CampaignResponse callBack;

    public RetrieveFeedTask(String API_URL)
    {
        this.API_URL = API_URL;
    }

    public RetrieveFeedTask(String API_URL, CampaignRequest.CampaignResponse callBack)
    {
        this.API_URL = API_URL;
        this.callBack = callBack;
    }

    protected void onPreExecute() {

    }


    protected String doInBackground(Void... urls) {
        // Do some validation here

        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);
        // TODO: check this.exception
        // TODO: do something with the feed
        if(callBack != null) {
            callBack.callBack(response);
        }

//            try {
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");
//                .
//                .
//                .
//                .
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
    }
}