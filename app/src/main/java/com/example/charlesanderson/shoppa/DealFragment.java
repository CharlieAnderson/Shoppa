package com.example.charlesanderson.shoppa;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
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
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                if (progress == 100) {
                    progressDialog.cancel();
                } else {
                    progressDialog.show();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        JSONObject post = ((TabActivity)getActivity()).getJsonData();
        if(post.has("url")) {
            try {
                webView.loadUrl(post.getString("url"));
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

}
