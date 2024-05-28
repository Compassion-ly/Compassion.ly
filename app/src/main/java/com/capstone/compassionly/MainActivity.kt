package com.capstone.compassionly

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.databinding.ActivityMainBinding
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.onboarding.OnBoardingActivity
import com.capstone.compassionly.presentation.feature.onboarding.viewmodel.OnBoardViewModel
import com.capstone.compassionly.repository.di.StateInjection

class MainActivity : AppCompatActivity() {
    private val onBoardViewModel : OnBoardViewModel by viewModels {
        StateInjection.onBoardInjection(this)
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        stateCheck()
    }

    private fun stateCheck() {
        onBoardViewModel.getOnBoardState().observe(this) {
            if (it.isNullOrBlank()) {
                val intent = Intent(this@MainActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                binding.main.setBackgroundColor(resources.getColor(R.color.md_theme_primaryContainer))
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}