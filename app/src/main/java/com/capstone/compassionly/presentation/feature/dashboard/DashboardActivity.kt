package com.capstone.compassionly.presentation.feature.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDashboardBinding
import com.capstone.compassionly.presentation.feature.dashboard.viewmodel.DashboardViewModel
import com.capstone.compassionly.presentation.feature.introduction_of_features.IntroductionFeaturesActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.PengantarJurusanActivity
import com.capstone.compassionly.presentation.feature.show_recommendation.ShowRecommendationActivity
import com.capstone.compassionly.presentation.feature.topic.TopicActivity
import com.capstone.compassionly.presentation.feature.topic_histories.TopicHistoriesActivity
import com.capstone.compassionly.presentation.feature.users_data.ProfileActivity
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Utils
import com.capstone.compassionly.utility.Utils.startActivityWithToken
import com.capstone.compassionly.utility.UtilsData
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel: DashboardViewModel by viewModels {
        CommonInjector.dashboardInjector(this)
    }

    private lateinit var auth: FirebaseAuth

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor()
        auth = Firebase.auth
        Utils.changeStatusBarColorWhite(this)
        viewModel.getToken().observe(this) { token ->
            if (token != null) {
                Log.d(TAG, "User Token: $token")
                viewModel.updateUserHistory(this@DashboardActivity, token)
                menu(token)

            } else {
                Log.e(TAG, "User Token not found")

            }
        }

    }

    private fun menu(userToken: String) {
        binding.apply {

            val sliderList = ArrayList<SlideModel>()
            UtilsData.getIntroduction().forEach {
                sliderList.add(SlideModel(it.cover))
            }

            slider.setImageList(sliderList, ScaleTypes.CENTER_CROP)
            slider.setItemClickListener(object: ItemClickListener {
                override fun doubleClick(position: Int) {

                }

                override fun onItemSelected(position: Int) {
                    val intent = Intent(this@DashboardActivity, IntroductionFeaturesActivity::class.java)
                    intent.putExtra("datas", UtilsData.getIntroduction()[position])
                    startActivity(intent)
                }

            })

            Log.d(TAG, "User token : $userToken")

            learnFeature.setOnClickListener {
                startActivityWithToken(TopicActivity::class.java, userToken)
            }
            binding.searchBar.setOnClickListener {
                startActivityWithToken(PengantarJurusanActivity::class.java, userToken)

            }
            pengantarJurusanFeature.setOnClickListener {
                startActivityWithToken(PengantarJurusanActivity::class.java, userToken)

            }
            binding.recomendationFeature.setOnClickListener {
                startActivityWithToken(ShowRecommendationActivity::class.java, userToken)

            }
            binding.fab.setOnClickListener {
                startActivityWithToken(TopicHistoriesActivity::class.java, userToken)

            }
            ivProfilePhoto.setOnClickListener {
                startActivityWithToken(ProfileActivity::class.java, userToken)

            }
        }
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.md_theme_primaryContainer)
    }

    override fun onStart() {
        super.onStart()
        val userDetail = auth.currentUser
        binding.apply {
            tvUsername.text = userDetail?.displayName
            Glide.with(applicationContext)
                .load(auth.currentUser?.photoUrl)
                .placeholder(resources.getDrawable(R.drawable.image_placeholder_profile, null))
                .into(ivProfilePhoto)
        }

    }

    companion object {
        const val TAG = "Dashboard Activity Test"
    }
}