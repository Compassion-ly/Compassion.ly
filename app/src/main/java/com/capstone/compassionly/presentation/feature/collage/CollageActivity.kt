package com.capstone.compassionly.presentation.feature.collage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityCollageBinding
import com.capstone.compassionly.models.CollageModel
import com.capstone.compassionly.presentation.feature.collage.viewmodel.CollageViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils

class CollageActivity : AppCompatActivity() {
    private var _binding: ActivityCollageBinding? = null
    private val binding get() = _binding!!

    private val collageViewModel : CollageViewModel by viewModels {
        CommonInjector.common(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityCollageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setView()
    }

    override fun onStart() {
        super.onStart()
        collageViewModel.getCollage(this@CollageActivity, this)
    }

    @Suppress("UNCHECKED_CAST")
    private fun setView () {
        collageViewModel.result.observe(this@CollageActivity) {
            when(it) {
                is Resources.Loading -> {
                    println("Loading....")
                }
                is Resources.Error -> {
                    val response = it.error.toString()
                    Utils.showToast(this@CollageActivity, response)
                }
                is Resources.Success -> {
                    val data = it.data as List<CollageModel>
                    println(it.data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}