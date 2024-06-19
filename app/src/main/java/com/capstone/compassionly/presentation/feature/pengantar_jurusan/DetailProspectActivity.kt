package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
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
        startActivity()
        btnBack()
    }


    private fun startActivity() {
        val prospectName = intent.getStringExtra(PROSPECT_NAME)
        val prospectDesc = intent.getStringExtra(PROSPECT_DESC)

        if (prospectName != null && prospectDesc != null) {
            binding.apply {
                toolbarTitle.text = prospectName
                tvDesc.text = prospectDesc
            }
        }
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val PROSPECT_NAME = "prospect name"
        const val PROSPECT_DESC = "prospect description"
    }
}