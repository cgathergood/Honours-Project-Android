package com.example.calum.honoursproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class FeedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getPosts();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    private void getPosts() {
        ParseQuery<ParseObject> query = new ParseQuery<>("PhotoTest");
        query.orderByDescending("createdAt");
        try {
            List<ParseObject> posts = query.find();
            for (ParseObject p : posts) {
                Log.d("debug", p.getCreatedAt().toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
