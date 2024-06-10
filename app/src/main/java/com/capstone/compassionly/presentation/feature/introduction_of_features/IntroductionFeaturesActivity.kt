package com.capstone.compassionly.presentation.feature.introduction_of_features

import android.os.Bundle
import android.util.ArrayMap
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityIntroductionFeaturesBinding
import com.capstone.compassionly.models.IntroductionModel
import com.capstone.compassionly.presentation.adapter.IntroductionAdapter

class IntroductionFeaturesActivity : AppCompatActivity() {
    private var _binding : ActivityIntroductionFeaturesBinding? = null
    private val binding get() = _binding!!
    private val mock : ArrayMap<String, Any> = ArrayMap<String, Any>()
    private var adapter : IntroductionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIntroductionFeaturesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.hasExtra("datas")) {
            setUpIntoDesign(intent.getParcelableExtra("datas")!!)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpIntoDesign(intro: IntroductionModel) {
        if (intro.step != null) {
            adapter = IntroductionAdapter(intro.step)
        }

        val layoutManagerOption = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.apply {
            featureName.text = intro.featureName
            description.text = intro.featureDescription
            rcStep.setHasFixedSize(true)
            rcStep.layoutManager = layoutManagerOption
            rcStep.adapter = adapter
        }

    }

}