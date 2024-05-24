package com.capstone.compassionly.presentation.feature.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.capstone.compassionly.MainActivity
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDashboardBinding
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor()

        val imageList = ArrayList<SlideModel>().apply {
            add(SlideModel(R.drawable.slider1))
            add(SlideModel(R.drawable.slider2))
            add(SlideModel(R.drawable.slider3))
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
        }

        binding.slider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        binding.learnFeature.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.pengantarJurusanFeature.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.recomendationFeature.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.md_theme_primaryContainer)

    }
}