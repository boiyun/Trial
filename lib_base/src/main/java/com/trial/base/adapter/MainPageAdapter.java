package com.trial.base.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;
/**
 * <pre>
 *     @author : Trial
 *     @time   : 7/7/21
 *     @desc   :
 *     @version: 1.0
 * </pre>
 */
public class MainPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragments;

    public MainPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
