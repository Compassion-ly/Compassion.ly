package com.capstone.compassionly.presentation.feature.users_data

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityFormCompleteUserProfileBinding
import com.capstone.compassionly.utility.Utils

class FormCompleteUserProfile : AppCompatActivity() {
    private var _binding : ActivityFormCompleteUserProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityFormCompleteUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utils.changeStatusBarColorWhite(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data = listOf("School1", "School2", "School3", "School4")
        val genderItem = listOf("Perempuan", "Laki-Laki")
        val adapterSchool = ArrayAdapter(this, R.layout.dropdown_item_layout, data)
        val adapterGender = ArrayAdapter(this, R.layout.dropdown_item_layout, genderItem)

        binding.apply {
            gender.setAdapter(adapterGender)
            autoCompleteSchool.setAdapter(adapterSchool)

        }


    }
}