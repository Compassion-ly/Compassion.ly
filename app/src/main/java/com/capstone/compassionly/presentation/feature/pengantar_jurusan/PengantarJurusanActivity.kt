package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityPengantarJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListMajorAdapter
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.PengantarJurusanViewModel
import com.capstone.compassionly.repository.di.MajorInjector
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class PengantarJurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengantarJurusanBinding
    private val viewModel: PengantarJurusanViewModel by viewModels {
        MajorInjector.majorInjector(this)
    }
    private lateinit var token: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPengantarJurusanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()
        }
        auth = Firebase.auth

        setup()
        setStatusBarColor()
        setListMajors()
        showRecyclerView()

    }

    private fun setup() {
        viewModel.getMajors(token)
    }


    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvMajors.layoutManager = layoutManager
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.customs_tatusbar)

    }


    private fun setListMajors() {
        val adapter = ListMajorAdapter()
        binding.rvMajors.adapter = adapter
        binding.rvMajors.layoutManager = LinearLayoutManager(this)

        viewModel.majors.observe(this) { majors ->
            adapter.submitList(majors)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val TAG = "PengantarJurusanActivity"
    }
}