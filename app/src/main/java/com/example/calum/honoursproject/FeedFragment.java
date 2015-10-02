package com.example.calum.honoursproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ParseObject> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_feed, container, false);

        listView = (ListView) v.findViewById(R.id.listView);

        postList = new ArrayList<>();

        adapter = new ListViewAdapter(getActivity(), postList);
        listView.setAdapter(adapter);
        getPosts();

        return v;
    }

    private void getPosts() {
        Log.d("test", "Post function");
        ParseQuery<ParseObject> query = new ParseQuery<>("PhotoTest");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                Log.d("test", String.valueOf(list.size()) + " posts received");
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        postList.add(i, list.get(i));
                    }
                }
                Log.d("test", "notified adapter");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
