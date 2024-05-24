package com.capstone.compassionly.presentation.feature.topic_histories

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityTopicHistoriesActtivityBinding
import com.capstone.compassionly.utility.Utils

class TopicHistoriesActivity : AppCompatActivity() {
    private var _binding : ActivityTopicHistoriesActtivityBinding? = null
    private val binding get() = _binding!!

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
    }
}