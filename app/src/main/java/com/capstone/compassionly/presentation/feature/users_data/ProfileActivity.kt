package com.capstone.compassionly.presentation.feature.users_data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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
import com.capstone.compassionly.utility.ResourcesResponse
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
            userVM.getMe().observe(this@ProfileActivity) {
                when (it) {
                    is ResourcesResponse.OnSuccess -> {
                        it.data?.data?.apply {
                            this.user.apply {
                                userFullName.text = "${this.firstName} ${this.lastName}"
                                userEmail.text = this.email
                                edPhoneNumber.text = this.phoneNumber
                            }
                            this.school?.apply {
                                edSchool.text = this.schoolName
                                edNpsn.text = this.npsn
                                edProvince.text = this.schoolProvince
                                edCity.text = this.schoolCity
                            }
                            this.schoolMajor?.apply {
                                edMajor.text = this.schoolMajorName
                            }
                        }
                    }

                    is ResourcesResponse.OnFailure -> {
                        Utils.showToast(this@ProfileActivity, it.message)
                    }

                    is ResourcesResponse.Loading -> {
                        indicatorProgress.visibility = View.VISIBLE
                    }
                }
            }
            btnLogout.setOnClickListener { removeToken() }
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
            getAccessToken().value.also {
                if (it != null) {
                    this.removeTokenApi(it)     // INVALIDATE API TOKEN
                }
            }
            removeAccessToken()                 // REMOTE TOKEN IN DATASTORE
        }
        mAuth.signOut()                         // SIGN OUT FIREBASE AUTHENTICATION
        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}