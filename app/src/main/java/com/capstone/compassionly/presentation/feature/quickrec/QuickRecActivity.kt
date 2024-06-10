package com.capstone.compassionly.presentation.feature.quickrec

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityQuickRecBinding
import com.capstone.compassionly.models.forsending.Data
import com.capstone.compassionly.models.forsending.QuickRecResponse
import com.capstone.compassionly.presentation.feature.dashboard.DashboardActivity
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.quickrec.viewmodel.QuickRecViewModel
import com.capstone.compassionly.presentation.feature.topic.TopicActivity
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils.startActivityWithToken

class QuickRecActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuickRecBinding
    private lateinit var token: String
    private val viewModel: QuickRecViewModel by viewModels {
        CommonInjector.dashboardInjector(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuickRecBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()
            setStatusBarColor()
            btnBack()
            binding.btnSubmit.setOnClickListener {
                requireMinChar()
            }
        } else {
            Toast.makeText(this, "Token not found. Please login again.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.white)
    }

    private fun requireMinChar() {
        val text1 = binding.edQuestion1.text.toString()
        val text2 = binding.edQuestion2.text.toString()
        val text3 = binding.edQuestion3.text.toString()

        val isValidLength = text1.length >= 50 && text2.length >= 50 && text3.length >= 50

        if (isValidLength) {
            val userDesc = "$text1 $text2 $text3"
            Log.d(TAG, "User desc : $userDesc")
            viewModel.sendUserDesc(token, userDesc).observe(this) { resources ->
                if (resources != null) {
                    when (resources) {
                        is Resources.Loading -> {
                            Log.d(TAG, "Loading...")
                        }

                        is Resources.Success -> {
                            Log.d(TAG, "$resources")
                            val quickRecResponse: QuickRecResponse? = resources.data as? QuickRecResponse
                            Log.d(TAG, "quickRecResponse : $quickRecResponse")

                            if (quickRecResponse?.data != null) {
                                val predictionList = quickRecResponse.data.prediction.orEmpty()

                                Log.d(TAG, "predictionList : $predictionList")
                                val newQuickRecResponse = QuickRecResponse(Data(prediction = predictionList))
                                Log.d(TAG, "newQuickRecResponse : $newQuickRecResponse")
                                viewModel.saveQuickRecResult(newQuickRecResponse)

//                                viewModel.getQuickRecResult().observe(this){result ->
//                                    Log.d(TAG, "getResult : $result")
//                                }

                                startActivityWithToken(QuickRecResultActivity::class.java, token)


                            } else {
                                Log.d(TAG, "quickRecResponse or quickRecResponse.data is null")
                            }
                        }
                        is Resources.Error -> {
                            Toast.makeText(
                                application, "Error: ${resources.error}", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }
        } else {
            binding.edQuestion1.error = "Minimal 50 karakter diperlukan"
            binding.edQuestion2.error = "Minimal 50 karakter diperlukan"
            binding.edQuestion3.error = "Minimal 50 karakter diperlukan"
        }
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val TAG = "QuickRecActivity"
    }
}