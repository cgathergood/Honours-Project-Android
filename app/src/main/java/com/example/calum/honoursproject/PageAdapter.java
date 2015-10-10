package com.example.calum.honoursproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Calum on 24/09/2015.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.tabCount = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FeedFragment();
            case 1:
                return new PostFragment();
            case 2:
                return new MapFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
