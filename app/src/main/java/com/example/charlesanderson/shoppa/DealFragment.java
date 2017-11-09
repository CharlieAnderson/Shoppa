package com.example.charlesanderson.shoppa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


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
        webView.getSettings().setJavaScriptEnabled(false);
        webView.loadUrl("http://bananarepublic.gap.com/");
        return view;
    }

}
