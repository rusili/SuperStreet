package com.rusili.superstreet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rusili.superstreet.favoritelist.ui.FavoriteListFragment
import com.rusili.superstreet.previewlist.ui.PreviewListFragment

private const val MAIN_PAGES = 2

class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? =
        when (position) {
            0 -> PreviewListFragment.newInstance()
            1 -> FavoriteListFragment.newInstance()
            else -> null
        }

    override fun getCount(): Int = MAIN_PAGES
}
