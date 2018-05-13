package com.example.charlesanderson.shoppa;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealFragment extends Fragment {


    public DealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal, container, false);
        WebView webView = (WebView)view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        JSONObject post = ((TabActivity)getActivity()).getJsonData();
        if(post.has("url")) {
            try {
                final String url = post.getString("url");
                webView.loadUrl(url);
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
                    }
                });
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

}
