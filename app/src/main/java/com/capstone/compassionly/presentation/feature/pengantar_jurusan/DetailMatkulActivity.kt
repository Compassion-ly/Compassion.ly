package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailMatkulBinding
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.DetailMatkulViewModel
import com.capstone.compassionly.repository.di.CommonInjector

class DetailMatkulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMatkulBinding
    private val viewModel: DetailMatkulViewModel by viewModels {
        CommonInjector.common(this)
    }
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        startActivity()
        btnBack()
    }

    private fun startActivity() {
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()

            val courseId = intent.getIntExtra(COURSE_ID, -1)
            if (courseId != -1) {
                findDetailCourse(token, courseId)
            } else {
                //
            }
        } else {
            Toast.makeText(this, "No token found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun findDetailCourse(token: String, id: Int) {
        viewModel.getDetailMatkul(token, id)
        viewModel.detailMatkul.observe(this) { detailMatkul ->
            binding.apply {
                toolbarTitle.text = detailMatkul.course?.courseName
                tvCoursedef.text = detailMatkul.course?.courseDefinition
                tvCourseExplain.text = detailMatkul.course?.courseExplain.toString()
                Glide.with(this@DetailMatkulActivity)
                    .load(detailMatkul.course?.courseImage)
                    .into(binding.ivCourseimg)
            }
        }
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val COURSE_ID = "course id"
    }
}