package com.capstone.compassionly.presentation.feature.topic_histories.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.databinding.FragmentHistoryBinding
import com.capstone.compassionly.presentation.adapter.ListHistoryAdapter
import com.capstone.compassionly.presentation.feature.topic_histories.view_model.TopicHistoryViewModel
import com.capstone.compassionly.repository.di.CommonInjector


class TopicHistoryFragment : Fragment() {

    private var _binding : FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val historyVM : TopicHistoryViewModel by viewModels {
        CommonInjector.common(requireContext())
    }
    private val adapters: ListHistoryAdapter = ListHistoryAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        searchListener()
        historyVM.resultHistory.observe(viewLifecycleOwner) {
            if (it != null) {
                println("masuk")
                adapters.save(it)
                binding.rcListHistory.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = adapters
                }
            }
        }

    }

    private fun initialization() {
        historyVM.apply {
            getUser().observe(viewLifecycleOwner) { user ->
                user[0].data?.user?.id.let {
                    getHistoryTopic(viewLifecycleOwner ,it!!)
                }
            }
        }
    }

    private fun searchListener() {
        historyVM.getUser().observe(viewLifecycleOwner) {
            val currentUser = it[0].data?.user?.id
            binding.edSearch.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    historyVM.getHistoryTopicBySearch(viewLifecycleOwner, s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}