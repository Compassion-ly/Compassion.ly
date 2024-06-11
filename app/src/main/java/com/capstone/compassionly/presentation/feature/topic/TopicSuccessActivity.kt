package com.capstone.compassionly.presentation.feature.topic

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityTopicSuccessBinding

class TopicSuccessActivity : AppCompatActivity() {
    private var _binding : ActivityTopicSuccessBinding? = null
    private val binding get () = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTopicSuccessBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.hasExtra("rate")) {
            println(intent.getIntExtra("rate", 0))
        }

        binding.btnMoreExploreTopic.setOnClickListener {
            val intent = Intent(this, TopicActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
    }

    @Deprecated("For ux user")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@TopicSuccessActivity, TopicActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}