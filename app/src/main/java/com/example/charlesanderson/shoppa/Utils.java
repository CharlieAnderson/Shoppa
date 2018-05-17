package com.example.charlesanderson.shoppa;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {
    public static String getHostname(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url.getHost();
    }
}
