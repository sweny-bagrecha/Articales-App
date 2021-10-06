package com.medibank.shop.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.medibank.shop.fragment.HeadlineFragment
import com.medibank.shop.fragment.SavedFragment
import com.medibank.shop.fragment.SourcesFragment

@Suppress("DEPRECATION")
internal class PagerAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HeadlineFragment()
            }
            1 -> {
                SourcesFragment()
            }
            2 -> {
                SavedFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}