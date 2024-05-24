package com.capstone.compassionly.presentation.feature.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        val imageList = ArrayList<SlideModel>().apply {
            add(SlideModel("https://bit.ly/2YoJ77H"))
            add(SlideModel("https://bit.ly/2BteuF2"))
            add(SlideModel("https://bit.ly/3fLJf72"))
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
        }

        binding.slider.setImageList(imageList, ScaleTypes.FIT)

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
}