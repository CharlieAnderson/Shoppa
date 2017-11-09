package com.example.charlesanderson.shoppa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends Fragment {

    List<String> titleList;
    AsyncJSON asyncJSON =new AsyncJSON();

    public DiscussionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discussion, container, false);
        this.titleList = new ArrayList<>();
        try {
            String message = asyncJSON.execute().get();
            processJSON(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titleList);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        return view;
    }

    public void processJSON(String message) {
        System.out.println("start process finish");
        try {
            JSONObject Jobject = new JSONObject(message);
            JSONObject data = Jobject.getJSONObject("data");
            JSONArray posts = data.getJSONArray("children");
            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i).getJSONObject("data");
                String title = post.getString("title");
                System.out.println("title: "+title);
                titleList.add(title);
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
