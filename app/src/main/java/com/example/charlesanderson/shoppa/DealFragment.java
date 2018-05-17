package com.example.charlesanderson.shoppa;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealFragment extends android.support.v4.app.Fragment {


    public DealFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal, container, false);
        WebView webView = view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setJavaScriptEnabled(true);
        try {
            String url = ((TabActivity)getActivity()).getJsonData().getString("url");
            webView.loadUrl(url);
            System.out.println(url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

}
