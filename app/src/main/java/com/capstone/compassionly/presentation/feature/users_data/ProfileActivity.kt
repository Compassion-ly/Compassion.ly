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
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityProfileBinding
import com.capstone.compassionly.models.local.LocalUser
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.users_data.view_model.UserViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Utils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!
    private val userVM: UserViewModel by viewModels {
        CommonInjector.common(this)
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

    }

    override fun onStart() {
        super.onStart()
        check()
    }

    @SuppressLint("SetTextI18n")
    private fun check() {
        val photoProfile = mAuth.currentUser?.photoUrl
        setPhotoProfile(photoProfile)
        val localUser = Observer<List<LocalUser>> { value ->
            binding.apply {
                if (value.isNotEmpty()) {
                    val it : LocalUser = value[0]
                    userFullName.text = "${it.data?.user?.firstName} ${it.data?.user?.lastName}"
                    userEmail.text = it.data?.user?.email
                    edPhoneNumber.text = it.data?.user?.phoneNumber
                    edGnder.text = it.data?.user?.gender
                    edSchool.text = it.data?.school?.schoolName
                    edNpsn.text = it.data?.school?.npsn
                    edMajor.text = it.data?.schoolMajor?.schoolMajorName
                    edProvince.text = it.data?.school?.schoolProvince
                    edCity.text = it.data?.school?.schoolCity
                } else {
                    Utils.showToast(this@ProfileActivity, "Berhasil Logout")
                }
            }
        }
        userVM.getDataUser().apply {
            observe(this@ProfileActivity, localUser)
        }
        binding.btnLogout.setOnClickListener {
            removeToken()
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
        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
        startActivity(intent)
        finishAffinity()
        userVM.apply {
            removeAccessToken()                 // REMOVE TOKEN IN DATASTORE
            mAuth.signOut()                     // SIGN OUT FIREBASE AUTHENTICATION
            deleteUser()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}