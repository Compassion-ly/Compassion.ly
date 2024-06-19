package com.capstone.compassionly.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.compassionly.presentation.feature.topic_histories.fragment.RecommendationHistoryFragment
import com.capstone.compassionly.presentation.feature.topic_histories.fragment.TopicHistoryFragment

class HistoryPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = TopicHistoryFragment()
            1 -> fragment = RecommendationHistoryFragment()
        }
        return fragment as Fragment
    }

}