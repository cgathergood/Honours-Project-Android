package com.example.calum.honoursproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap map;
    private HashMap<Marker, ParseObject> markerMap = new HashMap<Marker, ParseObject>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.refresh_item) {
            getPosts();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        map = mMapView.getMap();
        map.setMyLocationEnabled(true);

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View v = inflater.inflate(R.layout.map_marker_info, null);

                ParseObject parseObject = markerMap.get(marker);

                ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
                TextView tvUsername = (TextView) v.findViewById(R.id.username);
                TextView tvPlatform = (TextView) v.findViewById(R.id.platform);

                tvUsername.setText(parseObject.getString("user"));
                tvPlatform.setText(parseObject.getString("platform"));

                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        // Perform any camera updates here
        return v;
    }

    private void getPosts() {
        ParseQuery<ParseObject> query = new ParseQuery<>("PhotoTest");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                populateMap(list);
            }
        });
    }

    private void populateMap(List<ParseObject> list) {
        for (ParseObject p : list) {

            if (p.getString("platform").equals("Android")) {
                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(p.getDouble("lat"), p.getDouble("lon"))).icon(BitmapDescriptorFactory.defaultMarker(74)));
                markerMap.put(marker, p);
            } else {
                Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(p.getDouble("lat"), p.getDouble("lon"))).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                markerMap.put(marker, p);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getPosts();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
