package com.capstone.compassionly.presentation.feature.collage

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityDetailCollageBinding
import com.capstone.compassionly.models.MajorsItem
import com.capstone.compassionly.presentation.adapter.ItemMajorGridAdapter
import com.capstone.compassionly.presentation.feature.collage.viewmodel.CollageViewModel
import com.capstone.compassionly.repository.di.CommonInjector
import com.capstone.compassionly.utility.Resources
import com.capstone.compassionly.utility.Utils

class DetailCollageActivity : AppCompatActivity() {
    private var _binding: ActivityDetailCollageBinding? = null
    private val binding get() = _binding!!

    private val collageVM: CollageViewModel by viewModels {
        CommonInjector.common(this)
    }

    private var idGl: Int? = null
    private var nameG: String? = null
    private val majorAdapter: ItemMajorGridAdapter = ItemMajorGridAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDetailCollageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initialSetup()

        @Suppress("UNCHECKED_CAST")
        collageVM.getToken().observe(this) { token ->
            token?.let {
                idGl?.let { id ->
                    collageVM.apply {
                        getDetailCollageById(token, id)
//                            getDetailCollage(this, token, id).observe(this) { data ->
//                                when (data) {
//                                    is Resources.Loading -> {
//                                        binding.progressBar.visibility = View.VISIBLE
//                                    }
//
//                                    is Resources.Error -> {
//                                        binding.progressBar.visibility = View.GONE
//                                        val response = data.error.toString()
//                                        Utils.showToast(this@DetailCollageActivity, response)
//                                    }
//
//                                    is Resources.Success -> {
//                                        binding.progressBar.visibility = View.GONE
//                                        val result = data.data as DetailCollageModel
//                                        binding.capacity.text = result.capacity.toString()
//                                        binding.interest.text = result.interest.toString()
//                                        println("nama kampus $nameG")
//                                    }
//                                }
//                            }
//                        }
                    }
                }
            }
        }

        collageVM.detailCollage.observe(this) { res ->
            when (res) {
                is Resources.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resources.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val response = res.error.toString()
                    Utils.showToast(this@DetailCollageActivity, response)
                }

                is Resources.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val result = res.data as List<MajorsItem>
                    setUpMajorList(result)
                }
            }
        }

    }

    private fun setUpMajorList (data : List<MajorsItem>) {
        var sumInterest = 0
        data.forEach {
            if (it.majorInterest != null) {
                sumInterest += it.majorInterest
            }
        }

        val resultInterest = sumInterest / data.size

        majorAdapter.save(data)
        binding.apply {
            rcList.setHasFixedSize(true)
            rcList.layoutManager = GridLayoutManager(this@DetailCollageActivity, 2)
            rcList.adapter = majorAdapter
            interest.text = resultInterest.toString()
            capacity.text = "-"
        }

    }

    private fun initialSetup() {
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        nameG = name
        idGl = id
        binding.namaUniversitas.text = nameG
    }

    override fun onResume() {
        super.onResume()
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}