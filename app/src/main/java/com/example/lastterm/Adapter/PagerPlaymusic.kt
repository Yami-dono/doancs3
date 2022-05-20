package com.example.lastterm.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList

class PagerPlaymusic(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val fragments = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun AddFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}