package com.capstone.compassionly.presentation.feature.topic_histories

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityTopicHistoriesActtivityBinding
import com.capstone.compassionly.databinding.FragmentHistoryBinding
import com.capstone.compassionly.presentation.adapter.HistoryPagerAdapter
import com.capstone.compassionly.presentation.adapter.ListHistoryAdapter
import com.capstone.compassionly.presentation.feature.topic_histories.view_model.TopicHistoryViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Utils
import com.google.android.material.tabs.TabLayoutMediator

class TopicHistoriesActivity : AppCompatActivity() {
    private var _binding : ActivityTopicHistoriesActtivityBinding? = null
    private val binding get() = _binding!!

    private val historyVM : TopicHistoryViewModel by viewModels {
        CommonInjector.common(this)
    }
    private val adapters: ListHistoryAdapter = ListHistoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTopicHistoriesActtivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        Utils.changeStatusBarColorWhite(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialization()
        searchListener()
        historyVM.resultHistory.observe(this) {
            if (it != null) {
                println("masuk")
                adapters.save(it)
                binding.rcListHistory.apply {
                    layoutManager = LinearLayoutManager(this@TopicHistoriesActivity)
                    adapter = adapters
                }
            }
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.topic_history,
            R.string.rec_history
        )
    }

    private fun initialization() {
        historyVM.apply {
            getUser().observe(this@TopicHistoriesActivity) { user ->
                val current = user[0].data?.user?.id
                getHistoryTopic(this@TopicHistoriesActivity ,current!!)
            }
        }
    }

    private fun searchListener() {
        historyVM.getUser().observe(this@TopicHistoriesActivity) {
            val currentUser = it[0].data?.user?.id
            binding.edSearch.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    historyVM.getHistoryTopicBySearch(this@TopicHistoriesActivity, s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
    }
}