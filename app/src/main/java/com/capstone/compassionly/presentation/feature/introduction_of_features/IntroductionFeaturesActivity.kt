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
import com.capstone.compassionly.presentation.adapter.IntroductionAdapter

class IntroductionFeaturesActivity : AppCompatActivity() {
    private var _binding : ActivityIntroductionFeaturesBinding? = null
    private val binding get() = _binding!!
    private val mock : ArrayMap<String, Any> = ArrayMap<String, Any>()

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

        mockData()

        @Suppress("UNCHECKED_CAST")
        setUpIntoDesign((mock["systemRecommendation"] as ArrayMap<String, Any>))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun setUpIntoDesign(data: ArrayMap<String, Any>) {
        val adapter = IntroductionAdapter((data["picture"] as List<ArrayMap<String, Any>>))
        val layoutManagerOption = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        binding.apply {
            featureName.text = data["features"].toString()
            description.text = data["description"].toString()
            rcStep.setHasFixedSize(true)
            rcStep.layoutManager = layoutManagerOption
            rcStep.adapter = adapter
        }

    }

    private fun mockData() {
        val learn = ArrayMap<String, Any>()
        learn["feature"] = "Learn"
        learn["description"] = "Pada bagian ini, kamu akan mempelajari beberapa topik. Kamu dapat memilih untuk mempelajarinya atau melewatinya untuk mempelajari topik lainnya. Setelah belajar, kamu akan diminta untuk memberikan penilaian terhadap materi yang telah dipelajari dengan skala rating dari satu hingga lima bintang yang digunakan untuk menggambarkan tingkat kesukaan. Satu bintang menunjukkan ketidaksukaan, sementara lima bintang menunjukkan kesukaan yang sangat tinggi."
        learn["picture"] = mutableListOf("")

        val pengantar = ArrayMap<String, Any>()
        pengantar["features"] = "Pengantar Jurusan"
        pengantar["description"] = "Fitur pengantar jurusan merupakan salah satu layanan yang kami sediakan untuk memberikan sebuah informasi terkait jurusan - jurusan yang di indonesia. Di setiap jurusan yang kami berikan, akan ada beberapa informasi tambahan seperti penjelasan singkat terkait jurusan, mata kuliah, serta detail dari mata kuliah tersebut yang berkaitan erat dengan mata jurusan"
        pengantar["picture"] = mutableListOf("")

        val systemRecommendation = ArrayMap<String, Any>()
        systemRecommendation["features"] = "System Recommendation"
        systemRecommendation["description"] = "Fitur ini merupakan fitur utama yang kami sediakan untuk membantu para sistem untuk menentukan jurusan sesuai dengan minat bakat mereka"
        val content = ArrayMap<String, Any>()
        content["pictures"] = R.drawable.image_profile_sample
        content["description"] = "Step 1 blabla"
        val picture = mutableListOf(
            content, content, content
        )
        systemRecommendation["picture"] = picture

        mock["learn"] = learn
        mock["pengantar"] = pengantar
        mock["systemRecommendation"] = systemRecommendation
    }
}