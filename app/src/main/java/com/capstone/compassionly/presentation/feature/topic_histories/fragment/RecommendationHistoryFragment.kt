package com.capstone.compassionly.presentation.feature.topic_histories.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.databinding.FragmentRecommendationHistoryBinding
import com.capstone.compassionly.presentation.adapter.ListRecBidangHistoryAdapter
import com.capstone.compassionly.presentation.adapter.ListRecHistoryAdapter
import com.capstone.compassionly.presentation.feature.topic_histories.view_model.TopicHistoryViewModel
import com.capstone.compassionly.repository.di.CommonInjector

class RecommendationHistoryFragment : Fragment() {

    private var _binding : FragmentRecommendationHistoryBinding? = null
    private val binding get() = _binding!!
    private val historyVM : TopicHistoryViewModel by viewModels {
        CommonInjector.common(requireContext())
    }
    private val adapters: ListRecHistoryAdapter = ListRecHistoryAdapter()
    private val adapters2: ListRecBidangHistoryAdapter = ListRecBidangHistoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendationHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyVM.apply {
            getMajorResult().observe(viewLifecycleOwner){ data ->
                adapters.save(data)
                binding.rcListHistory.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = adapters
                }
            }
            getFieldResult().observe(viewLifecycleOwner) { data ->
                adapters2.save(data)
                binding.rcBidang.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = adapters2
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}