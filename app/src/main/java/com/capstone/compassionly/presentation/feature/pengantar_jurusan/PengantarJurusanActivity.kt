package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityPengantarJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListMajorAdapter
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Major

class PengantarJurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengantarJurusanBinding
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

        val listJurusan = DataDummyUtil.getMajors()

        setStatusBarColor()
        setListMajor(listJurusan)
        showRecyclerView()

    }

    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvMajors.layoutManager = layoutManager
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.customs_tatusbar)

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setListMajor(majors: List<Major>) {
        val adapter = ListMajorAdapter()
        adapter.submitList(majors)
        binding.rvMajors.adapter = adapter

    }
}