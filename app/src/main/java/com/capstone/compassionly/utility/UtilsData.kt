package com.capstone.compassionly.utility

import com.capstone.compassionly.R
import com.capstone.compassionly.models.IntroductionModel
import com.capstone.compassionly.models.Step

object UtilsData {

    fun getIntroduction() : List<IntroductionModel> {
        val data = mutableListOf<IntroductionModel>()

        val quickRecommendation = IntroductionModel (
            R.drawable.slide4,
            "Quick Recommedation",
            "Fitur ini merupkaan fitur tambahan yang dimana sistem akan memberikan rekomendasi bidang sesuai dengan minat anda melalui deskripsi anda yang diberikan",
            getStepQuickRecommendation()
        )
        val systemRecommendation = IntroductionModel(
            R.drawable.slider3,
            "Sistem Rekomendasi",
            "Fitur ini merupakan fitur utama yang kami sediakan untuk membantu para sistem untuk menentukan jurusan sesuai dengan minat bakat anda",
            getStepSystemRecommendation()
        )
        val pengantar = IntroductionModel(
            R.drawable.slider2,
            "Pengantar Jurusan",
            "Fitur pengantar jurusan merupakan salah satu layanan yang kami sediakan untuk memberikan sebuah informasi terkait jurusan - jurusan yang di indonesia. Di setiap jurusan yang kami berikan, akan ada beberapa informasi tambahan seperti penjelasan singkat terkait jurusan, mata kuliah, serta detail dari mata kuliah tersebut yang berkaitan erat dengan mata jurusan.",
            null
        )
        val learn = IntroductionModel(
            R.drawable.slider1,
            "Learn",
            "Pada bagian ini, kamu akan mempelajari beberapa topik. Kamu dapat memilih untuk mempelajarinya atau melewatinya untuk mempelajari topik lainnya. Setelah belajar, kamu akan diminta untuk memberikan penilaian terhadap materi yang telah dipelajari dengan skala rating dari satu hingga lima bintang yang digunakan untuk menggambarkan tingkat kesukaan. Satu bintang menunjukkan ketidaksukaan, sementara lima bintang menunjukkan kesukaan yang sangat tinggi.",
            null
        )
        data.add(learn)
        data.add(pengantar)
        data.add(systemRecommendation)
        data.add(quickRecommendation)
        return data
    }

    private fun getStepSystemRecommendation(): List<Step>{
        val data = mutableListOf<Step>()
        val stepSystemRecommendation1 = Step(
            R.drawable.image_profile_sample,
            "Masuk ke learn untuk belajar beberapa materi yang diberikan"
        )
        val stepSystemRecommendation2 = Step(
            R.drawable.image_profile_sample,
            "Berikan rating di setiap materi yang anda baca"
        )
        val stepSystemRecommendation3 = Step(
            R.drawable.image_profile_sample,
            "Setelah membaca dan memberikan rating pada setiap materi yang dibaca, anda dapat menekan tombol sistem rekomendasi yang ada pada halaman utama"
        )
        data.add(stepSystemRecommendation1)
        data.add(stepSystemRecommendation2)
        data.add(stepSystemRecommendation3)
        return data
    }

    private fun getStepQuickRecommendation(): List<Step> {
        val data = mutableListOf<Step>()
        val stepQuick1 = Step(
            R.drawable.image_profile_sample,
            "Masuk kedalam menu Quick Recommendation"
        )
        val stepQuick2 = Step(
            R.drawable.image_profile_sample,
            "Masukan beberapa input sesuai dengan keinginan anda"
        )
        val stepQuick3 = Step(
            R.drawable.image_profile_sample,
            "Anda akan dapat melihat hasil rekomendasi dari sistem kami sesuai dengan minat anda"
        )
        data.add(stepQuick1)
        data.add(stepQuick2)
        data.add(stepQuick3)
        return data
    }

}