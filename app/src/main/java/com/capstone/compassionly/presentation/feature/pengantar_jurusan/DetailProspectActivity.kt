package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailMatkulBinding
import com.capstone.compassionly.databinding.ActivityDetailProspectBinding

class DetailProspectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProspectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailProspectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prospectId = intent.getIntExtra(PROSPECT_ID, -1)
        if (prospectId != -1) {
//            val prospect = findDetailCourse(prospectId)
//            prospect?.let { displayCourseDetails(it) }
        }else{
            //
        }
    }


    private fun findDetailCourse(prospectId: Int): Any {
        TODO("Not yet implemented")
    }

    companion object {
        const val PROSPECT_ID = "prospect id"
    }
}