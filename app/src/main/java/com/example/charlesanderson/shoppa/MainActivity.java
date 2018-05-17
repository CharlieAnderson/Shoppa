package com.example.charlesanderson.shoppa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String AUTH_URL =  "https://www.reddit.com/api/v1/authorize.compact?client_id=%s" +
                                            "&response_type=code&state=%s&redirect_uri=%s&" +
                                            "duration=permanent&scope=identity";
    private static final String CLIENT_ID = "ABCDEFGHIJKLM012345-AA";
    private static final String REDIRECT_URI = "https://www.example.com/my_redirect";
    private static final String STATE = "MY_RANDOM_STRING_1";
    private static final String ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";
    private static final String TAG = "MainActivity";

    private JSONObject jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncJSON asyncJSON =new AsyncJSON();
        try {
            String message = asyncJSON.execute().get();
            processJSON(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Shoppa");

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        ListFragment listFragment = new ListFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_container, listFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(getIntent()!=null && getIntent().getAction().equals(Intent.ACTION_VIEW)) {
            Uri uri = getIntent().getData();
            if(uri.getQueryParameter("error") != null) {
                String error = uri.getQueryParameter("error");
                Log.e(TAG, "An error has occurred : " + error);
            } else {
                String state = uri.getQueryParameter("state");
                if(state.equals(STATE)) {
                    String code = uri.getQueryParameter("code");
                    getAccessToken(code);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startSignIn(View view) {
        String url = String.format(AUTH_URL, CLIENT_ID, STATE, REDIRECT_URI);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // use okhttp for reddit oauth
    private void getAccessToken(String code) {
        OkHttpClient client = new OkHttpClient();
        String authString = CLIENT_ID + ":";
        String encodedAuthString = Base64.encodeToString(authString.getBytes(),
                Base64.NO_WRAP);
        Request request = new Request.Builder()
                .addHeader("User-Agent", "Sample App")
                .addHeader("Authorization", "Basic " + encodedAuthString)
                .url(ACCESS_TOKEN_URL)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                        "grant_type=authorization_code&code=" + code +
                                "&redirect_uri=" + REDIRECT_URI))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "ERROR: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                JSONObject data = null;
                try {
                    data = new JSONObject(json);
                    String accessToken = data.optString("access_token");
                    String refreshToken = data.optString("refresh_token");

                    Log.d(TAG, "Access Token = " + accessToken);
                    Log.d(TAG, "Refresh Token = " + refreshToken);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startTabActivity(int position) {
        Intent intent = new Intent(this, TabActivity.class);
        try {
            intent.putExtra("data", jsonData.getJSONArray("children")
                    .getJSONObject(position)
                    .getJSONObject("data")
                    .toString());
        } catch(JSONException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    public void processJSON(String message) {
        System.out.println("start process finish");
        try {
            JSONObject Jobject = new JSONObject(message);
            jsonData = Jobject.getJSONObject("data");
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public JSONObject getJsonData() {
        return jsonData;
    }
}
