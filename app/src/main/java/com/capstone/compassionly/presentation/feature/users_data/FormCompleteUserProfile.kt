package com.capstone.compassionly.presentation.feature.users_data

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityFormCompleteUserProfileBinding
import com.capstone.compassionly.models.SuccessResponse
import com.capstone.compassionly.models.UserModel
import com.capstone.compassionly.presentation.adapter.CustomAutoCompleteAdapter
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.users_data.view_model.UserViewModel
import com.capstone.compassionly.repository.di.UserInjector
import com.capstone.compassionly.utility.ResourcesResponse
import com.capstone.compassionly.utility.Utils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class FormCompleteUserProfile : AppCompatActivity() {
    private var _binding : ActivityFormCompleteUserProfileBinding? = null
    private val binding get() = _binding!!
    private val userVM : UserViewModel by viewModels {
        UserInjector.userInjector(this)
    }
    private lateinit var auth : FirebaseAuth
    private var schoolId : Int? = null
    private var schoolMajorId : Int? = null

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
        auth = Firebase.auth
        binding.apply {
            btnSubmit.setOnClickListener {
                updateProfileData()
            }
        }

        setAllDropdownAdapter()

    }

    private fun setAllDropdownAdapter() {
        val genderItem = listOf("Perempuan", "Laki-Laki")
        val adapterGender = ArrayAdapter(this, R.layout.dropdown_item_layout, genderItem)

        binding.apply {
            userVM.getSchoolList().observe(this@FormCompleteUserProfile) {
                if (it != null) {
                    val schoolList = mutableListOf<String>()
                    it.forEach { data ->
                        schoolList.add("${data.schoolName}, ${data.schoolCity}, ${data.schoolProvince}")
                    }
                    val schoolAdapter = CustomAutoCompleteAdapter(this@FormCompleteUserProfile, R.layout.dropdown_item_layout, schoolList)
                    autoCompleteSchool.apply {
                        setAdapter(schoolAdapter)
                        setOnItemClickListener { parent, view, position, id ->
                            schoolId = it[position].id
                        }
                    }
                }
            }
            userVM.getSchoolMajorList().observe(this@FormCompleteUserProfile) {
                if (it != null) {
                    val schoolMajorList = mutableListOf<String>()
                    it.forEach { data ->
                        data.schoolMajorName?.let { it1 -> schoolMajorList.add(it1) }
                    }
                    val schoolMajor = CustomAutoCompleteAdapter(this@FormCompleteUserProfile, R.layout.dropdown_item_layout, schoolMajorList)
                    autoCompleteSchoolMajor.apply {
                        setAdapter(schoolMajor)
                        setOnItemClickListener { parent, view, position, id ->
                            schoolMajorId = it[position].id
                        }
                    }
                }
            }
            gender.setAdapter(adapterGender)
        }
    }

    private fun updateProfileData() {
        binding.apply {
            val firstName = edFirstName.text.toString()
            val lastName = edLastName.text.toString()
            val phoneNumber = edPhoneNumber.text.toString()
            val gender = gender.text.toString()

            println("$schoolId, $schoolMajorId")
            if (schoolId != null && schoolMajorId != null) {
                userVM.update(
                    firstName, lastName, phoneNumber, gender, schoolId!!, schoolMajorId!!
                ).observe(this@FormCompleteUserProfile) {
                    responseAction(it)
                }
            }
        }
    }

    private fun responseAction(response: ResourcesResponse<SuccessResponse<UserModel>>?) {
        when (response) {
            is ResourcesResponse.Loading -> {
                binding.loadingIndicator.visibility = View.VISIBLE
            }
            is ResourcesResponse.OnFailure -> {
                showToast(response.message)
            }
            is ResourcesResponse.OnSuccess -> {
                val intent = Intent(this@FormCompleteUserProfile, DashboardActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            null -> {
                showToast("Something went wrong")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}