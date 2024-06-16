package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListCollegeAdapter
import com.capstone.compassionly.presentation.adapter.ListCourseAdapter
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Course
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Major
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.DetailJurusanViewModel
import com.capstone.compassionly.repository.di.CommonInjector

class DetailJurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailJurusanBinding
    private val viewModel: DetailJurusanViewModel by viewModels {
        CommonInjector.common(this)
    }
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailJurusanBinding.inflate(layoutInflater)
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
    }

    private fun startActivity() {
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()

            val majorId = intent.getIntExtra(MAJOR_ID, -1)
            if (majorId != -1) {
                findCollegesMajor(token, majorId)
                findDetailMajor(token, majorId)
            } else {
                //
            }

            val listMatkul = DataDummyUtil.getCourses()
            setListCourse(listMatkul)
            showRecyclerViewCourse()
        } else {
            Toast.makeText(this, "No token found", Toast.LENGTH_SHORT).show()
        }

    }


    private fun findDetailMajor(token: String, id: Int) {

        viewModel.getDetailMajor(token, id)
        viewModel.detailMajor.observe(this) { detailMajor ->
            val peminat = getString(R.string.peminat_format, detailMajor.majorInterest)
            val forwho = getString(R.string.forwho_format, detailMajor.forWho.toString())
            binding.apply {
                Glide.with(binding.root.context)
                    .load(detailMajor.majorImage)
                    .into(binding.ivMajor)
                tvMajorName.text = detailMajor.majorName.toString()
                tvPeminat.text = peminat
                tvMajorDef.text = detailMajor.majorDefinition.toString()
                tvForwho.text = forwho
                tvMajorLevel.text = detailMajor.majorLevel.toString()
            }
        }
    }

    private fun findCollegesMajor(token: String, majorId: Int) {
        viewModel.getCollegesByMajor(token, majorId)
        Log.d(PengantarJurusanActivity.TAG, "findCollegesMajor(), token: $token")
        setListColleges()
        showRecyclerViewCollege()
    }

    private fun showRecyclerViewCourse() {
        val layoutManager = object: LinearLayoutManager(this) {
            override fun canScrollVertically() = false
        }
        binding.rvCourses.layoutManager = layoutManager
    }

    private fun showRecyclerViewCollege() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvColleges.layoutManager = layoutManager
    }

    private fun displayMajorDetails(major: Major) {
        binding.tvMajorName.text = major.majorName
        binding.tvMajorDef.text = major.majorDefinition
        Glide.with(this)
            .load(major.majorImage)
            .into(binding.ivMajor)
    }

    private fun setListCourse(majors: List<Course>) {
        val adapter = ListCourseAdapter()
        adapter.submitList(majors)
        binding.rvCourses.adapter = adapter
    }

    private fun setListColleges() {
        val adapter = ListCollegeAdapter()
        binding.rvColleges.adapter = adapter
        binding.rvColleges.layoutManager = LinearLayoutManager(this)

        viewModel.colleges.observe(this) { colleges ->
            Log.d(TAG, "$colleges")
            adapter.submitList(colleges)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val MAJOR_ID = "major id"
        const val TAG = "DetailJurusanActivity"
    }
}