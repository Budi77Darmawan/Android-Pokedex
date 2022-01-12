package com.bd_drmwan.pokedex.presentation.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    private var listAdapter = mutableListOf<Fragment>()

    fun setupFragment(fragment: List<Fragment>) {
        listAdapter.clear()
        listAdapter.addAll(fragment)
    }

    override fun getItemCount(): Int = listAdapter.size

    override fun createFragment(position: Int): Fragment {
        return listAdapter[position]
    }
}