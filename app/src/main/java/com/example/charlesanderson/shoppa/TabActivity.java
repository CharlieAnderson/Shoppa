package com.example.charlesanderson.shoppa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class TabActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private JSONObject jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        try {
            Intent intent = getIntent();
            String jsonString = intent.getStringExtra("data");
            jsonData = new JSONObject(jsonString);
        } catch(JSONException e) {
            e.printStackTrace();
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                JSONObject post = jsonData;
                switch (position) {
                    case 0:
                        if (post.has("url")) {
                            try {
                                final String url = post.getString("url");
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                                fab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                                        CustomTabsIntent customTabsIntent = builder.build();
                                        customTabsIntent.launchUrl(TabActivity.this, Uri.parse(url));
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                    case 1:
                        if (post.has("permalink")) {
                            try {
                                final String url = Constants.REDDIT_URL + post.getString("permalink");
                                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                                fab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                                        CustomTabsIntent customTabsIntent = builder.build();
                                        customTabsIntent.launchUrl(TabActivity.this, Uri.parse(url));
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;

                        }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public JSONObject getJsonData() {
        return jsonData;
    }
}
