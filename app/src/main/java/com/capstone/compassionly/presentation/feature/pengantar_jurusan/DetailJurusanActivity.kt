package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.compassionly.databinding.ActivityDetailJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListCourseAdapter
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Course
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.DataDummyUtil
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Major

class DetailJurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailJurusanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailJurusanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val majorId = intent.getIntExtra(MAJOR_ID, -1)
        if (majorId != -1) {
            val major = findDetailMajor(majorId)
            major?.let { displayMajorDetails(it) }
        }else{
            //
        }

        val listMatkul = DataDummyUtil.getCourses()
        setListCourse(listMatkul)
        showRecyclerView()
    }

    private fun findDetailMajor(id: Int): Major? {
        return DataDummyUtil.getMajors().find { it.majorId == id }    }
    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvCourses.layoutManager = layoutManager
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

    companion object{
        const val MAJOR_ID ="major id"
    }
}