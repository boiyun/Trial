package com.trial.base.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class VPAdapter(var fm: FragmentManager, private val list_fragment: List<Fragment>) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return list_fragment[position]
    }

    override fun getCount(): Int {
        return list_fragment.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Fragment {
        val fragment = super.instantiateItem(container, position) as Fragment
        fm.beginTransaction().show(fragment).commitAllowingStateLoss()
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val fragment = list_fragment[position]
        fm.beginTransaction().hide(fragment).commit()
    }
}