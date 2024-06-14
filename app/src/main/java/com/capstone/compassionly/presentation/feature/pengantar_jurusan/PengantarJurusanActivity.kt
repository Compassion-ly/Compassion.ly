package com.capstone.compassionly.presentation.feature.pengantar_jurusan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.compassionly.R
import com.capstone.compassionly.databinding.ActivityPengantarJurusanBinding
import com.capstone.compassionly.presentation.adapter.ListMajorAdapter
import com.capstone.compassionly.presentation.feature.login.LoginActivity
import com.capstone.compassionly.presentation.feature.pengantar_jurusan.viewmodel.PengantarJurusanViewModel
import com.capstone.compassionly.repository.di.CommonInjector

class PengantarJurusanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPengantarJurusanBinding
    private val viewModel: PengantarJurusanViewModel by viewModels {
        CommonInjector.common(this)
    }
    private lateinit var token: String
    private lateinit var searchMajor: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPengantarJurusanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }


        if (intent.hasExtra("token")) {
            token = intent.getStringExtra("token").toString()
            Log.d(TAG,"token : $token")
            setup()
            setStatusBarColor()
            setListMajors()
            showRecyclerView()
            binding.searchBar.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    searchMajor = s.toString()
                    findMajor(searchMajor)

                    Log.d(TAG, "find major : $searchMajor")
                }


            })
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


//        binding.searchBar.requestFocus()
//        binding.searchBar.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                searchMajor = binding.searchBar.text.toString()
//                Log.d(TAG, "find major : $searchMajor")
//                findMajor(searchMajor)
//                return@setOnEditorActionListener true
//            }
//            false
//        }

    }

    private fun setup() {
        viewModel.getMajors(token)
    }


    private fun showRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvMajors.layoutManager = layoutManager
    }

    private fun setStatusBarColor() {
        window.statusBarColor = getColor(R.color.customs_tatusbar)

    }

    private fun setListMajors() {
        val adapter = ListMajorAdapter(token)
        binding.rvMajors.adapter = adapter
        binding.rvMajors.layoutManager = LinearLayoutManager(this)

        viewModel.majors.observe(this) { majors ->
            adapter.submitList(majors)
        }
    }

    private fun setListFindMajors() {
        val adapter = ListMajorAdapter(token)
        binding.rvMajors.adapter = adapter
        binding.rvMajors.layoutManager = LinearLayoutManager(this)

        viewModel.findMajor.observe(this) { majors ->
            adapter.submitList(majors)
        }
    }

    private fun findMajor(search_query: String) {
        viewModel.getMajor(token, search_query)
        Log.d(TAG, "findMajor(), token: $token")
        setListFindMajors()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val TAG = "PengantarJurusanActivity"
    }
}