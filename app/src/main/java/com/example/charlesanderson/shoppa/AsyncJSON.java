package com.example.charlesanderson.shoppa;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by charlesanderson on 10/25/17.
 */

public class AsyncJSON extends AsyncTask<String, Void, String> {
    JSONArray posts;

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        try {
            String url = params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response responses = null;
            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonData = responses.body().string();
            return jsonData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "null json message";
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
        try {
            JSONObject Jobject = new JSONObject(message);
            JSONObject data = Jobject.getJSONObject("data");
            posts = data.getJSONArray("children");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
