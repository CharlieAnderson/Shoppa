package com.example.charlesanderson.shoppa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        initializeData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new RecyclerView.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BLARG");
                ((MainActivity)getActivity()).startTabActivity();
            }
        });
        return view;
    }

    private void initializeData() {
        posts = new ArrayList<>();
        posts.add(new Post("good deal on shoes", "5", android.R.drawable.ic_menu_week));
        posts.add(new Post("50% off stuff", "21", android.R.drawable.ic_dialog_map));
        posts.add(new Post("free shipping at nordstrom", "0", android.R.drawable.ic_menu_save));
        posts.add(new Post("good deal on shoes", "5", android.R.drawable.ic_menu_week));
        posts.add(new Post("50% off stuff", "21", android.R.drawable.ic_dialog_map));
        posts.add(new Post("free shipping at nordstrom", "0", android.R.drawable.ic_menu_save));
    }
}
