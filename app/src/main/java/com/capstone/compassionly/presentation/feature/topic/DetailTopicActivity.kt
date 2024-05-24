package com.capstone.compassionly.presentation.feature.topic

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailBinding
import com.capstone.compassionly.databinding.LayoutDialogRateTopicBinding
import com.capstone.compassionly.models.TopicModel
import com.capstone.compassionly.presentation.adapter.DetailTopicAdapter
import com.capstone.compassionly.utility.Utils

class DetailTopicActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = Utils.dialogInstance(this)

        if (intent.hasExtra("topic")) {
            val data = intent.getParcelableExtra<TopicModel>("topic")
            println(data?.material)
            data?.let {
                setToDisplay(it, dialog)
            }
        }

    }

    private fun setToDisplay(data: TopicModel, dialog: Dialog) {
        val adapter = DetailTopicAdapter()
        adapter.save(data)
        binding.pagerDetail.apply {
            this.adapter = adapter
            this.isUserInputEnabled = false
            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    statePage(data, position, dialog)
                }
            })
        }
    }

    private fun statePage(data: TopicModel, position: Int, dialog: Dialog) {
        val dialogRate =  LayoutDialogRateTopicBinding.inflate(layoutInflater)
        when (position) {
            data.material.size - 1 -> {
                binding.apply {
                    nextButton.apply {
                        this.text = getString(R.string.done)
                        this.setOnClickListener {
                            dialog.setContentView(dialogRate.root)
                            dialog.show()
                            dialogAction(dialogRate, dialog)
                        }
                    }
                    backButton.setOnClickListener {  binding.pagerDetail.currentItem = position - 1  }
                }
            }
            0 -> {
                binding.apply {
                    backButton.setOnClickListener { finish() }
                    nextButton.text = getString(R.string.next)
                    nextButton.setOnClickListener { binding.pagerDetail.currentItem = position + 1 }
                }
            }
            else -> {
                binding.nextButton.apply {
                    this.text = getString(R.string.next)
                    this.setOnClickListener { binding.pagerDetail.currentItem = position + 1 }
                }
                binding.backButton.setOnClickListener { binding.pagerDetail.currentItem = position - 1 }
            }
        }
    }

    private fun dialogAction(binding: LayoutDialogRateTopicBinding, dialog: Dialog) {
        dialog.setCancelable(false)
        binding.apply {
            btnSend.setOnClickListener {
                val intent = Intent(this@DetailTopicActivity, TopicSuccessActivity::class.java)
                intent.putExtra("rate", rate.rating.toInt())
                startActivity(intent)
                finishAffinity()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}