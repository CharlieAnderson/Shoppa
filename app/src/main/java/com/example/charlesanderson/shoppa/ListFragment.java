package com.example.charlesanderson.shoppa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private List<Post> posts;
    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initializeData(((MainActivity)getActivity()).getJsonData());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(posts);
        recyclerView.setAdapter(adapter);;
        return view;
    }

    private void initializeData(JSONObject data) {
        posts = new ArrayList<>();
        try {
            JSONArray postData = data.getJSONArray("children");
            for (int i = 0; i < postData.length(); i++) {
                JSONObject post = postData.getJSONObject(i).getJSONObject("data");
                String title = post.getString("title");
                String comments = post.getString("num_comments");
                String imgUrl;
                String webViewUrl = post.getString("url");
                String commentsUrl = Constants.REDDIT_URL + post.getString("permalink");
                /*
                if(post.has("preview")) {
                    imgUrl = post.getJSONObject("preview")
                            .getJSONArray("images")
                            .getJSONObject(0)
                            .getJSONObject("source")
                            .getString("url");
                }
                else {
                    imgUrl = post.getString("thumbnail");
                }
                */
                System.out.println("url: "+webViewUrl);
                posts.add(new Post(title, comments, webViewUrl, commentsUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
