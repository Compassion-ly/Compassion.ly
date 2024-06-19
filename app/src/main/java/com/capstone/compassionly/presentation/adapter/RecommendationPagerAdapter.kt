package com.capstone.compassionly.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.compassionly.presentation.feature.show_recommendation.fragment.JurusanFragment
import com.capstone.compassionly.presentation.feature.show_recommendation.fragment.KetertarikanFragment

class RecommendationPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = KetertarikanFragment()
            1 -> fragment = JurusanFragment()
        }
        return fragment as Fragment
    }

}