package com.example.calum.honoursproject;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class PostFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setSelected(!fab.isSelected());
                fab.setImageResource(fab.isSelected() ? R.drawable.animated_plus : R.drawable.animated_minus);
                Drawable drawable = fab.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
    }
}
