package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailMatkulBinding
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.DetailMatkulViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Utils

class DetailMatkulActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMatkulBinding
    private val viewModel: DetailMatkulViewModel by viewModels {
        CommonInjector.common(this)
    }
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        startActivity()
        btnBack()
    }

    private fun startActivity() {
        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()

            val courseId = intent.getIntExtra(COURSE_ID, -1)
            if (courseId != -1) {
                findDetailCourse(token, courseId)
            } else {
                Utils.showToast(this, getString(R.string.data_not_found))
                finish()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.token_not_found))
                setMessage(R.string.ask_login)
                setPositiveButton(R.string.signIn) { _, _ ->
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            }
        }
    }

    private fun findDetailCourse(token: String, id: Int) {
        viewModel.getDetailMatkul(token, id, this)
        viewModel.detailMatkul.observe(this) { detailMatkul ->
            binding.apply {
                toolbarTitle.text = detailMatkul.course?.courseName
                tvCoursedef.text = detailMatkul.course?.courseDefinition


                detailMatkul.course?.courseExplain?.let {
                    tvCourseExplain.text = detailMatkul.course.courseExplain as CharSequence?
                }?:run {
                    tvCourseExplain.text = ""
                }


                binding.apply {
                    detailMatkul.course?.courseImage?.let {
                        Glide.with(binding.root.context)
                            .load(detailMatkul.course.courseImage)
                            .into(binding.ivCourseimg)
                    } ?: run {
                        Glide.with(binding.root.context)
                            .load("https://www.quipper.com/id/blog/wp-content/uploads/2020/10/175-min-min.png")
                            .into(binding.ivCourseimg)
                    }
                }
            }
        }
    }

    private fun btnBack() {
        binding.iconBack.setOnClickListener {
            finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val COURSE_ID = "course id"
    }
}