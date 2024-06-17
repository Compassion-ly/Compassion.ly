package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailJurusanBinding
import com.capstone.compassionly.models.CollegesItem
import com.capstone.compassionly.models.CoursesItem
import com.capstone.compassionly.models.ProspectsItem
import com.capstone.compassionly.presentation.adapter.ListCollegeAdapter
import com.capstone.compassionly.presentation.adapter.ListCourseAdapter
import com.capstone.compassionly.presentation.adapter.ListProspectAdapter
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.DetailJurusanViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Utils

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
                findDetailMajor(token, majorId)
            } else {
                Utils.showToast(this,getString(R.string.data_not_found))
                finish()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.token_not_found))
                setMessage(R.string.ask_login)
                setPositiveButton(R.string.signIn) { _, _ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }

    }


    private fun findDetailMajor(token: String, id: Int) {

        viewModel.getDetailMajor(token, id, this)
        viewModel.detailMajor.observe(this) { detailMajor ->
            val peminat = getString(R.string.peminat_format, detailMajor.major?.majorInterest)
            val listCourses = detailMajor.courses
            val listProspect = detailMajor.prospects
            val listCollege = detailMajor.colleges

            binding.apply {
                Glide.with(binding.root.context)
                    .load(detailMajor.major?.majorImage)
                    .into(binding.ivMajor)
                tvMajorName.text = detailMajor.major?.majorName.toString()
                tvPeminat.text = peminat
                tvMajorDef.text = detailMajor.major?.majorDefinition.toString()
                tvForwho.text = detailMajor.major?.forWho.toString()
                tvMajorLevel.text = detailMajor.major?.majorLevel.toString()
            }

            setListCourse(token, listCourses)
            showRecyclerViewCourse()

            setListProspect(listProspect)
            showRecyclerViewProspect()

            setListCollege(token, listCollege)
            showRecyclerViewCollege()

        }
    }

    private fun showRecyclerViewCourse() {
        val layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically() = false
        }
        binding.rvCourses.layoutManager = layoutManager
    }

    private fun showRecyclerViewProspect() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvProspect.layoutManager = layoutManager
    }

    private fun showRecyclerViewCollege() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvColleges.layoutManager = layoutManager
    }


    private fun setListCourse(token: String, courses: List<CoursesItem?>?) {
        val adapter = ListCourseAdapter(token)
        adapter.submitList(courses)
        binding.rvCourses.adapter = adapter
    }

    private fun setListProspect(prospect: List<ProspectsItem?>?) {
        val adapter = ListProspectAdapter()
        adapter.submitList(prospect)
        binding.rvProspect.adapter = adapter
    }

    private fun setListCollege(token: String, college: List<CollegesItem?>?) {
        val adapter = ListCollegeAdapter(token)
        adapter.submitList(college)
        binding.rvColleges.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val MAJOR_ID = "major id"
        const val TAG = "DetailJurusanActivity"
    }
}