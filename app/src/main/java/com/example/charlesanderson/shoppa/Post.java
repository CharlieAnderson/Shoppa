package com.example.charlesanderson.shoppa;

/**
 * Created by charlesanderson on 11/6/17.
 */

public class Post {
    String title;
    String comments;
    String url;
    String permalink;

    Post(String title, String comments, String url, String permalink) {
        this.title = title;
        this.comments = comments;
        this.url = url;
        this.permalink = permalink;
    }
}


