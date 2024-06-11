package com.capstone.compassionly.presentation.feature.topic

//import com.capstone.compassionly.models.ImageMaterial
//import com.capstone.compassionly.models.Material
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.capstone.compassionly.databinding.ActivityTopicBinding
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.adapter.RandomTopicAdapter
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.topic.view_model.TopicViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.SwipeControlListener
import com.capstone.compassionly.utility.SwipeDirection

class TopicActivity : AppCompatActivity() {
    private var _binding: ActivityTopicBinding? = null
    private val binding get() = _binding!!
    private val swipeControlListener by lazy {
        SwipeControlListener()
    }
    private val topicVM: TopicViewModel by viewModels {
        CommonInjector.common(this)
    }
    private val adapter = RandomTopicAdapter()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTopicBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        swipeControlListener.setSwipeDirection(SwipeDirection.LEFT)
        additionalDisplay()

        topicVM.getToken().observe(this@TopicActivity) {
            topicVM.getTopic(it, 2, 0).observe(this@TopicActivity) { data ->
                topicVM.getUserData().observe(this@TopicActivity) { listUser ->
                    if (listUser.isNotEmpty()) {
                        topicVM.getTopicHistory(listUser[0].data?.user?.id!!)
                            .observe(this@TopicActivity) { local ->
                                when (data) {
                                    is Resources.Loading -> {
                                        Log.d("TopicActivity", "Loading...")
                                        binding.progressbar.visibility = View.VISIBLE
                                        binding.btnPlay.visibility = View.GONE
                                        binding.containerInstruction.visibility = View.GONE
                                    }

                                    is Resources.Success -> {
                                        // Display state
                                        binding.progressbar.visibility = View.GONE

                                        println("masi $local")
                                        val ss = data.data as List<TopicModel>
                                        val listTopic = mutableListOf<Int>()
                                        if (local.isNotEmpty()) {
                                            local.forEach { localHistory ->
                                                listTopic.add(localHistory.topicId!!)
                                            }
                                        }
                                        val filter = ss.filter { topic ->
                                            !listTopic.contains(topic.id)
                                        }
                                        println("hasil filter $filter")
                                        if (filter.isEmpty()) {
                                            binding.btnPlay.visibility = View.GONE
                                            binding.containerInstruction.visibility = View.GONE
                                            binding.notFoundContainer.visibility = View.VISIBLE
                                            binding.btnRefresh.setOnClickListener {
                                                finish();
                                                overridePendingTransition(0, 0);
                                                startActivity(intent);
                                                overridePendingTransition(0, 0);
                                            }
                                        } else {
                                            binding.btnPlay.visibility = View.VISIBLE
                                            binding.containerInstruction.visibility = View.VISIBLE
                                            binding.notFoundContainer.visibility = View.GONE
                                            adapter.save(filter)
                                            binding.btnPlay.setOnClickListener { showDetailTopic(filter) }
                                        }
                                    }

                                    is Resources.Error -> {
                                        binding.progressbar.visibility = View.GONE
                                        binding.notFoundContainer.visibility = View.VISIBLE
                                        binding.btnRefresh.setOnClickListener {
                                            finish();
                                            overridePendingTransition(0, 0);
                                            startActivity(intent);
                                            overridePendingTransition(0, 0);
                                        }
                                    }
                                }
                            }
                    } else {
                        when (data) {
                            is Resources.Loading -> {
                                Log.d("TopicActivity", "Loading...")
                                binding.progressbar.visibility = View.VISIBLE
                                binding.btnPlay.visibility = View.GONE
                                binding.containerInstruction.visibility = View.GONE
                            }

                            is Resources.Success -> {
                                val ss = data.data as List<TopicModel>
                                if (ss.isEmpty()) {
                                    binding.btnPlay.visibility = View.GONE
                                    binding.containerInstruction.visibility = View.GONE
                                    binding.notFoundContainer.visibility = View.VISIBLE
                                }

                                binding.btnPlay.visibility = View.VISIBLE
                                binding.containerInstruction.visibility = View.VISIBLE
                                binding.notFoundContainer.visibility = View.GONE
                                adapter.save(ss)
                                binding.btnPlay.setOnClickListener { showDetailTopic(ss) }
                            }

                            is Resources.Error -> {
                                binding.progressbar.visibility = View.GONE
                                binding.notFoundContainer.visibility = View.VISIBLE
                                binding.btnRefresh.setOnClickListener {
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(intent);
                                    overridePendingTransition(0, 0);
                                }
                            }
                        }
                    }
                }
            }
        }


        val rc = binding.vpTopic[0] as? RecyclerView
        rc?.addOnItemTouchListener(swipeControlListener)

        binding.apply {
            vpTopic.adapter = adapter
        }

    }

    private fun showDetailTopic(data: List<TopicModel>) {
        val currentPosition = binding.vpTopic.currentItem
        val dataOnPosition = data[currentPosition]

        println("dataon poision $dataOnPosition")
        val intent = Intent(this@TopicActivity, DetailTopicActivity::class.java)
        intent.putExtra("topic", dataOnPosition)
        startActivity(intent)
    }

    private fun additionalDisplay() {
        binding.vpTopic.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.containerInstruction.visibility = View.VISIBLE
                    }

                    else -> {
                        binding.containerInstruction.visibility = View.GONE
                    }
                }
            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@TopicActivity, DashboardActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}