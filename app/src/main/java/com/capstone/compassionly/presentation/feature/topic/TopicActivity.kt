package com.capstone.compassionly.presentation.feature.topic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityTopicBinding
import com.capstone.compassionly.models.ImageMaterial
import com.capstone.compassionly.models.Material
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.adapter.RandomTopicAdapter
import com.capstone.compassionly.utility.SwipeControlListener
import com.capstone.compassionly.utility.SwipeDirection

class TopicActivity : AppCompatActivity() {
    private var _binding: ActivityTopicBinding? = null
    private val binding get() = _binding!!
    private val swipeControlListener by lazy {
        SwipeControlListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTopicBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        swipeControlListener.setSwipeDirection(SwipeDirection.LEFT)
        additionalDisplay()

        val adapter = RandomTopicAdapter()
        adapter.save(setDataDummy())

        val rc = binding.vpTopic[0] as? RecyclerView
        rc?.addOnItemTouchListener(swipeControlListener)

        binding.apply {
            vpTopic.adapter = adapter
            btnPlay.setOnClickListener { showDetailTopic() }
        }

    }

    private fun setDataDummy(): List<TopicModel> {
        val listImageMaterial = listOf(
            ImageMaterial(R.drawable.image_system_recommendation_sample),
            ImageMaterial(R.drawable.image_system_recommendation_sample),
            ImageMaterial(R.drawable.image_system_recommendation_sample),
        )
        val listMaterial = listOf(
            Material(1,"halo ini adalah main material", listImageMaterial),
            Material(2, "halo ini adalahsudgfasgdbasdgbasdgaikgnasdfg", listImageMaterial),
        )
        val data = mutableListOf<TopicModel>()
        data.add(TopicModel(1, "Coding", R.drawable.image_profile_sample, "ini short description", listMaterial))
        data.add(TopicModel(1, "Coding", R.drawable.image_profile_sample, "ini short description", listMaterial))
        data.add(TopicModel(1, "Coding", R.drawable.image_profile_sample, "ini short description", listMaterial))

        return data
    }

    private fun showDetailTopic() {
        val currentPosition = binding.vpTopic.currentItem
        val dataOnPosition = setDataDummy()[currentPosition]


        println(dataOnPosition)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}