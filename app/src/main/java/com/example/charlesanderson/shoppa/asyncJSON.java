package com.example.charlesanderson.shoppa;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by charlesanderson on 10/25/17.
 */

public class asyncJSON extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String[] params) {
        // do above Server call here
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://www.reddit.com/r/frugalmalefashion/hot.api?limit=1")
                    .build();
            Response responses = null;

            try {
                responses = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonData = responses.body().string();
            JSONObject Jobject = new JSONObject(jsonData);
            JSONObject data = Jobject.getJSONObject("data");
            System.out.println(data.getJSONObject("children").get("title"));
            // https://www.reddit.com/search.json?q=title%3Acomputer+title%3Awrist+title%3Apad+title%3Areview&limit=10
            // https://www.reddit.com/r/pics/search.json?q=kittens&sort=new&limit=1
            /*
            for (int i = 0; i < Jarray.length(); i++) {
                System.out.println(Jarray.getClass().toString());
                String object = Jarray.getString(i);
                System.out.println("blah"+object);
            }
            */
            return Jobject.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "some message";
    }

    @Override
    protected void onPostExecute(String message) {
        //process message
    }
}
