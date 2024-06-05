package com.capstone.compassionly.presentation.feature.users_data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityProfileBinding
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.users_data.view_model.UserViewModel
import com.capstone.compassionly.repository.di.UserInjector
import com.capstone.compassionly.utility.Utils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!
    private val userVM: UserViewModel by viewModels {
        UserInjector.userInjector(this)
    }
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utils.changeStatusBarColorWhite(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mAuth = Firebase.auth
        setDisplayView()
    }

    @SuppressLint("SetTextI18n")
    private fun setDisplayView() {
        val photoProfile = mAuth.currentUser?.photoUrl
        setPhotoProfile(photoProfile)
        binding.apply {
            userVM.getDataUser().observe(this@ProfileActivity) {
                it[0].apply {
                    userFullName.text = "${this.firstName} ${this.lastName}"
                    userEmail.text = this.email
                    edPhoneNumber.text = this.phoneNumber
                    edGnder.text = this.gender
                    edSchool.text = this.schoolName
                    edNpsn.text = this.npsn
                    edMajor.text = this.schoolMajorName
                    edProvince.text = this.schoolProvince
                    edCity.text = this.schoolCity
                    btnLogout.setOnClickListener { removeToken() }
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setPhotoProfile(uri: Uri?) {
        Glide.with(this)
            .load(uri)
            .placeholder(resources.getDrawable(R.drawable.image_placeholder_profile))
            .into(binding.profilePicture)
    }

    private fun removeToken() {
        userVM.apply {
            removeAccessToken()                 // REMOVE TOKEN IN DATASTORE
            mAuth.signOut()                     // SIGN OUT FIREBASE AUTHENTICATION

            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            startActivity(intent)
            userVM.deleteUser()
            finishAffinity()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}