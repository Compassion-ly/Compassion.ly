package com.capstone.compassionly.presentation.feature.quickrec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityQuickRecResultBinding
import com.capstone.compassionly.presentation.adapter.ListCategoryAdapter
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.quickrec.viewmodel.QuickRecViewModel
import com.capstone.compassionly.repository.di.CommonInjector

class QuickRecResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuickRecResultBinding
    private val viewModel: QuickRecViewModel by viewModels {
        CommonInjector.dashboardInjector(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuickRecResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            btnBack()
            setListInterest()
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setListInterest() {
        Log.d("QuickRecResult", "setListInterest()")

        val adapter = ListCategoryAdapter()
        binding.rvInterests.adapter = adapter
        binding.rvInterests.layoutManager = LinearLayoutManager(this)

        viewModel.getQuickRecResult()
        viewModel.interests.observe(this) { interests ->
            adapter.submitList(interests)
            Log.d("QuickRecResult", "result : $interests")

        }
    }

}