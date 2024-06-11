package com.capstone.compassionly.presentation.feature.topic

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.compassionly.databinding.ActivityDetailBinding
import com.capstone.compassionly.databinding.LayoutDialogRateTopicBinding
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.adapter.ImageMaterialAdapter
import com.capstone.compassionly.presentation.feature.topic.view_model.TopicViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils

class DetailTopicActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private val topicVM: TopicViewModel by viewModels {
        CommonInjector.common(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = Utils.dialogInstance(this)

        if (intent.hasExtra("topic")) {
            val data = intent.getParcelableExtra<TopicModel>("topic")
            println(data)
            data?.let {
                setToDisplay(it, dialog)
            }
        }

        // After rating state
        topicVM.resultPost.observe(this) {
            when (it) {
                is Resources.Loading -> {
                    Log.d("TopicActivity", "Loading...")
                }

                is Resources.Success -> {
                    val resultBody = it.data
                    println("result rate $resultBody")

                    val intent = Intent(this@DetailTopicActivity, TopicSuccessActivity::class.java)
                    finishAffinity()
                    startActivity(intent)
                }

                is Resources.Error -> {
                    Toast.makeText(
                        application,
                        "Error: ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    private fun setToDisplay(data: TopicModel, dialog: Dialog) {
//        val adapter = DetailTopicAdapter(this@DetailTopicActivity)
        val adapter = ImageMaterialAdapter()
        val dataImage = mutableListOf<String>()
        dataImage.add(data.topicImage!!)
        dataImage.add(data.topicImage2!!)
        adapter.save(dataImage)
//        binding.pagerDetail.apply {
//            this.adapter = adapter
//            this.isUserInputEnabled = false
//            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    statePage(data, position, dialog)
//                }
//            })
//        }
        binding.apply {
            pagerImageDetail.adapter = adapter
            dotsIndicator.attachTo(pagerImageDetail)
            headTopic.text = data.topicName
            mainTopic.text = data.topicExplanation
            binding.nextButton.setOnClickListener {
                val dialogRate =  LayoutDialogRateTopicBinding.inflate(layoutInflater)
                dialog.setContentView(dialogRate.root)
                dialog.show()
                dialogAction(dialogRate, dialog, data.id!!)
            }
        }
    }

    private fun statePage(data: TopicModel, position: Int, dialog: Dialog) {
//        val dialogRate =  LayoutDialogRateTopicBinding.inflate(layoutInflater)
//        when (position) {
//            data.material.size - 1 -> {
//                binding.apply {
//                    nextButton.apply {
//                        this.text = getString(R.string.done)
//                        this.setOnClickListener {
//                            dialog.setContentView(dialogRate.root)
//                            dialog.show()
//                            dialogAction(dialogRate, dialog)
//                        }
//                    }
//                    backButton.setOnClickListener {  binding.pagerDetail.currentItem = position - 1  }
//                }
//            }
//            0 -> {
//                binding.apply {
//                    backButton.setOnClickListener { finish() }
//                    nextButton.text = getString(R.string.next)
//                    nextButton.setOnClickListener { binding.pagerDetail.currentItem = position + 1 }
//                }
//            }
//            else -> {
//                binding.nextButton.apply {
//                    this.text = getString(R.string.next)
//                    this.setOnClickListener { binding.pagerDetail.currentItem = position + 1 }
//                }
//                binding.backButton.setOnClickListener { binding.pagerDetail.currentItem = position - 1 }
//            }
//        }
    }

    private fun dialogAction(binding: LayoutDialogRateTopicBinding, dialog: Dialog, topicId: Int) {
        dialog.setCancelable(false)
        binding.apply {
            btnSend.setOnClickListener {
//                dialog.hide()
                binding.progressBar.visibility = View.VISIBLE
                postRate(rate.rating.toInt(), topicId)
            }
        }
    }

    private fun postRate(rating: Int, topicId: Int) {
        topicVM.apply {
            getToken().observe(this@DetailTopicActivity) { token ->
                if (token.isNullOrEmpty()) {
                    Utils.showToast(this@DetailTopicActivity,"Login expired")
                } else {
                    postToken(token, rating, topicId)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}