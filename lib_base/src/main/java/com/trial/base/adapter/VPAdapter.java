package com.trial.base.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class VPAdapter extends FragmentPagerAdapter {


    private List<Fragment> list_fragment;
    public FragmentManager fm;

    public VPAdapter(FragmentManager fm, List<Fragment> list_fragment) {
        super(fm);
        this.fm = fm;
        this.list_fragment = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        if(!fragment.isAdded()){
            fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = list_fragment.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }
}
