package com.capstone.compassionly.presentation.feature.quickrec

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDashboardBinding
import com.capstone.compassionly.databinding.ActivityQuickRecBinding
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity

class QuickRecActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuickRecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuickRecBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setStatusBarColor()
        btnBack()
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.white)
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}