package com.example.okabe.nexttry.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class SectionPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> navBarList = new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return navBarList.get(position);
    }

    @Override
    public int getCount() {

        return navBarList.size();
    }

    public void addItem(Fragment fragment) {

        navBarList.add(fragment);
    }
}
