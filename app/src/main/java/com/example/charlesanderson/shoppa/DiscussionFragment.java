package com.example.charlesanderson.shoppa;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends android.support.v4.app.Fragment {

    List<String> titleList;

    public DiscussionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discussion, container, false);
        this.titleList = new ArrayList<>();
        AsyncJSON asyncJSON = new AsyncJSON();
        try {
            String message = asyncJSON.execute().get();
            getComments(((TabActivity)getActivity()).getJsonData());
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

    public void getComments(JSONObject post) {
        try {
            String title = post.getString("title");
            titleList.add(title);
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
