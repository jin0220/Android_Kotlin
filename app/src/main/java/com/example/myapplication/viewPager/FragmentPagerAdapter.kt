package com.example.myapplication.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPagerAdapter(val list:List<Fragment>, fragmentActivity:FragmentActivity)
                                                        : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = list.size
    override fun createFragment(position: Int) = list.get(position)
}