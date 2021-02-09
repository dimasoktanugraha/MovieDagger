package com.artera.movieexpertdagger.favorite.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.artera.movieexpertdagger.R
import com.artera.movieexpertdagger.favorite.child.FavMovieFragment
import com.artera.movieexpertdagger.favorite.child.FavTvFragment

class FavoritePagerAdapter (private val mContext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    @StringRes
    private val tabTitles = intArrayOf(R.string.movie, R.string.tv_show)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavMovieFragment()
            else -> {
                FavTvFragment()
            }
        }
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }
}