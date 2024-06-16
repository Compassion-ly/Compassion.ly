package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailMatkulBinding
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.Course
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.datadummy.DataDummyUtil

class DetailMatkulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMatkulBinding
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

        startActivity()
    }

    private fun startActivity(){
        val courseId = intent.getIntExtra(COURSE_ID, -1)
        if (courseId != -1) {
            val course = findDetailCourse(courseId)
            course?.let { displayCourseDetails(it) }
        }else{
            //
        }
    }
    private fun findDetailCourse(id: Int): Course? {
        return DataDummyUtil.getCourses().find { it.courseId == id }    }

    private fun displayCourseDetails(course: Course) {
        binding.tvCoursename.text = course.courseName
        binding.tvCoursedef.text = course.courseDefinition
        Glide.with(this)
            .load(course.courseImage)
            .into(binding.ivCourseimg)
        binding.tvCourseExplain.text = course.courseExplain
    }
    companion object {
        const val COURSE_ID = "course id"
    }
}